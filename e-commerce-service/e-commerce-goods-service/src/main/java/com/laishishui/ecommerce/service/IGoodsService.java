package com.laishishui.ecommerce.service;

import com.laishishui.ecommerce.common.TableId;
import com.laishishui.ecommerce.goods.DeductGoodsInventory;
import com.laishishui.ecommerce.goods.GoodsInfo;
import com.laishishui.ecommerce.goods.SimpleGoodsInfo;
import com.laishishui.ecommerce.vo.PageSimpleGoodsInfo;

import java.util.List;

/**
 * <h1>商品微服务相关服务接口定义</h1>
 * @author tachai on 2022/1/16 6:56 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public interface IGoodsService {
    /**
     * <h2>根据TableId 查询商品详细信息</h2>
     * @param tableId
     * @return
     */
    List<GoodsInfo> getGoodsInfoByTableId(TableId tableId);

    /**
     * <h2>获取分页的商品信息</h2>
     * @param page
     * @return
     */
    PageSimpleGoodsInfo getSimpleGoodsInfoByPage(int page);

    /**
     * <h2>根据TableId 查询简单商品信息</h2>
     * @param tableId
     * @return
     */
    List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId);

    /**
     * <h2>扣减商品库存</h2>
     * @param deductGoodsInventories
     * @return
     */
    Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories);
}
