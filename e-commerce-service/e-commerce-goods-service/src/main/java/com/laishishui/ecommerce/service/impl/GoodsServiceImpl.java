package com.laishishui.ecommerce.service.impl;

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.common.TableId;
import com.laishishui.ecommerce.constant.GoodsConstant;
import com.laishishui.ecommerce.dao.EcommerceGoodsDao;
import com.laishishui.ecommerce.entity.EcommerceGoods;
import com.laishishui.ecommerce.goods.DeductGoodsInventory;
import com.laishishui.ecommerce.goods.GoodsInfo;
import com.laishishui.ecommerce.goods.SimpleGoodsInfo;
import com.laishishui.ecommerce.service.IGoodsService;
import com.laishishui.ecommerce.vo.PageSimpleGoodsInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author tachai on 2022/1/17 7:35 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements IGoodsService {

    private final StringRedisTemplate redisTemplate;
    private final EcommerceGoodsDao ecommerceGoodsDao;

    public GoodsServiceImpl(StringRedisTemplate redisTemplate, EcommerceGoodsDao ecommerceGoodsDao) {
        this.redisTemplate = redisTemplate;
        this.ecommerceGoodsDao = ecommerceGoodsDao;
    }

    @Override
    public List<GoodsInfo> getGoodsInfoByTableId(TableId tableId) {

        // 详细的商品信息，不能从redis擦除cache 中去拿
        List<Long> ids= tableId.getIds().stream()
                .map(TableId.Id::getId)
                .collect(Collectors.toList());
        log.info("get goods info by ids:[{}]", JSON.toJSONString(ids));
        List<EcommerceGoods> ecommerceGoods = IterableUtils.toList(
                ecommerceGoodsDao.findAllById(ids)
        );

        return ecommerceGoods.stream()
                .map(EcommerceGoods::toGoodsInfo)
                .collect(Collectors.toList());
    }

    @Override
    public PageSimpleGoodsInfo getSimpleGoodsInfoByPage(int page) {
        // 分页不能去redis  cache中去拿
        if(page<=1){
            page=1;// 默认是第一页
        }
        // 这里分页的规则（你可以自由修改）：1页10调数据，按照id倒序
        Pageable pageable = PageRequest.of(
                page-1,
                10,
                Sort.by("id").descending());
        Page<EcommerceGoods> orderPage = ecommerceGoodsDao.findAll(pageable);
        // 是否还有更多页：总页数是否大于当前给定的页
        boolean hasMore = orderPage.getTotalPages()>page;


        return new PageSimpleGoodsInfo(
                orderPage.getContent().stream()
                .map(EcommerceGoods::toSimple)
                .collect(Collectors.toList()),
                hasMore
        );
    }

    @Override
    public List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId) {

        // 获取商品的简单信息，可以从redis cache 中去拿，拿不到需要从DB中获取并保存到Redis里面
        // Redis 中的KV 都是字符串类型
        List<Object> goodIds = tableId.getIds().stream()
                .map(i->i.getId().toString())
                .collect(Collectors.toList());

        List<Object> cachedSimpleGoodsInfos= redisTemplate.opsForHash()
                .multiGet(GoodsConstant.ECOMMERCE_GOODS_DICT_KEY,goodIds);

        // 如果从Redis 中查到了商品信息，分两种情况去操作
        if(CollectionUtils.isNotEmpty(cachedSimpleGoodsInfos)){
            // 1.如果从缓存中查询出所有需要的 SimpleGoodsInfo
            if(cachedSimpleGoodsInfos.size()==goodIds.size()){
                log.info("get simple goods info by ids (from cache): [{}]",
                        JSON.toJSONString(goodIds));
                return parseCachedGoodsInfo(cachedSimpleGoodsInfos);
            }else {
                // 2. 一半从数据表中获取（right）,一半从redis cache 中获取（left）
                List<SimpleGoodsInfo> left = parseCachedGoodsInfo(cachedSimpleGoodsInfos);
                // 取差集： 传递进来的参数-缓存中查到的=缓存中没有的
                Collection<Long> subtractIds=CollectionUtils.subtract(
                        goodIds.stream()
                        .map(g->Long.valueOf(g.toString()))
                        .collect(Collectors.toList()),
                        left.stream().map(SimpleGoodsInfo::getId).collect(Collectors.toList())
                    );
                // 缓存中没有的，查询数据表并缓存
                List<SimpleGoodsInfo> right = queryGoodsFromDbAndCacheToRedis(
                        new TableId(subtractIds.stream()
                                .map(TableId.Id::new)
                                .collect(Collectors.toList()))
                );
                // 合并left 和right 并返回
                log.info("get simple goods info by ids(from db and cache): [{}]",
                JSON.toJSONString(subtractIds));

                return new ArrayList<>(CollectionUtils.union(left,right));
            }

        }else {
            // 从redis 里面什么都没有查到
            return queryGoodsFromDbAndCacheToRedis(tableId);
        }

    }


    /**
     * <h2>将缓存中的数据反序列哈JAVA pojo 对象</h2>
     * @param cacheSimpleGoodInfo
     * @return
     */
    private List<SimpleGoodsInfo> parseCachedGoodsInfo(List<Object>  cacheSimpleGoodInfo){
        return cacheSimpleGoodInfo.stream()
                .map(s->JSON.parseObject(s.toString(),SimpleGoodsInfo.class))
                .collect(Collectors.toList());
    }

    /**
     * <h2>从数据表中查询数据，并缓存到Redis中</h2>
     * @return
     */
    private List<SimpleGoodsInfo> queryGoodsFromDbAndCacheToRedis(TableId tableId){

        // 从数据表中查询数据并做转换
        List<Long> ids= tableId.getIds().stream()
                .map(TableId.Id::getId)
                .collect(Collectors.toList());

        log.info("get simple goods info by ids (from db): [{}]",
                JSON.toJSONString(ids));
        List<EcommerceGoods> ecommerceGoods = IterableUtils.toList(
                ecommerceGoodsDao.findAllById(ids)
        );
        List<SimpleGoodsInfo> result = ecommerceGoods.stream()
                .map(EcommerceGoods::toSimple)
                .collect(Collectors.toList());
        // 将结果缓存，下一次可以直接从redis cache 中查询
        log.info("cache goods info: [{}]",JSON.toJSONString(ids));
        Map<String,String> id2JsonObejct = new HashMap<>(result.size());
        result.forEach(g->id2JsonObejct.put(
                g.getId().toString(),JSON.toJSONString(g)
        ));

        // 保存到Redis 中
        redisTemplate.opsForHash().putAll(GoodsConstant.ECOMMERCE_GOODS_DICT_KEY,
                id2JsonObejct);
        return result;
    }

    @Override
    public Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories) {
        // 检验下参数是否合法
        deductGoodsInventories.forEach(d->{
            if(d.getCount()<=0){
                throw new RuntimeException("purchase goods count need >0");
            }
        });

        List<EcommerceGoods> ecommerceGoods = IterableUtils.toList(
                ecommerceGoodsDao.findAllById(
                        deductGoodsInventories.stream()
                        .map(DeductGoodsInventory::getGoodsId)
                        .collect(Collectors.toList())
                )
        );
        // 根据传递的goodsIds 查询不到商品对象，抛异常
        if(CollectionUtils.isEmpty(ecommerceGoods)){
            throw new RuntimeException("can not found any goods by request");
        }

        // 查询出来的商品数量与传递的不一致，抛异常
        if(ecommerceGoods.size()!=deductGoodsInventories.size()){
            throw new RuntimeException("request is not valid");
        }

        // goodsId -> DeductGoodsInventory
        Map<Long, DeductGoodsInventory> goodsId2Inventory =
                deductGoodsInventories.stream().collect(
                        Collectors.toMap(DeductGoodsInventory::getGoodsId, Function.identity())
                );

        // 检查是不是可以扣减库存，再去扣减库存
        ecommerceGoods.forEach(g->{
            Long currentInventory = g.getInventory();
            Integer needDeductInventory = goodsId2Inventory.get(g.getId()).getCount();
            if(currentInventory<needDeductInventory){
                log.error("goods inventory is not enough:[{}],[{}]",
                        currentInventory,needDeductInventory);
                throw new RuntimeException("goods inventory is not enough:"+g.getId());
            }
            // 扣减库存
            g.setInventory(currentInventory-needDeductInventory);
            log.info("deduct goods inventory: [{}],[{}],[{}]",g.getId(),
                    currentInventory,g.getInventory());
        });

        ecommerceGoodsDao.saveAll(ecommerceGoods);
        log.info("deduct goods inventory done");
        return true;
    }
}
