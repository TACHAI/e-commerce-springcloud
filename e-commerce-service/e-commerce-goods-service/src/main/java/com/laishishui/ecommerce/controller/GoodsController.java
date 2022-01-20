package com.laishishui.ecommerce.controller;

import com.laishishui.ecommerce.common.TableId;
import com.laishishui.ecommerce.goods.DeductGoodsInventory;
import com.laishishui.ecommerce.goods.GoodsInfo;
import com.laishishui.ecommerce.goods.SimpleGoodsInfo;
import com.laishishui.ecommerce.service.IGoodsService;
import com.laishishui.ecommerce.vo.PageSimpleGoodsInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h1>商品微服务对外暴露的功能服务 API接口</h1>
 * @author tachai on 2022/1/20 10:03 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Api(tags="商品微服务功能接口")
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final IGoodsService goodsService;

    public GoodsController(IGoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @ApiOperation(value = "详细商品信息",notes = "根据tableId查询详细商品信息", httpMethod = "POST")
    @PostMapping("/goods-info")
    public List<GoodsInfo> getGoodsInfoByTableId(@RequestBody TableId tableId){
        return goodsService.getGoodsInfoByTableId(tableId);
    }
    @ApiOperation(value = "简单商品信息",notes ="获取分页的简单商品信息",httpMethod = "GET")
    @GetMapping("/page-simple-goods-info")
    public PageSimpleGoodsInfo getSimpleGoodsInfoByPage(@RequestParam(required = false,defaultValue = "1") int page){
        return goodsService.getSimpleGoodsInfoByPage(page);
    }

    @ApiOperation(value = "简单商品信息",notes = "根据tableId获取简单商品信息",httpMethod = "POST")
    @PostMapping("/simple-goods-info")
    public List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(@RequestBody TableId tableId){
        return goodsService.getSimpleGoodsInfoByTableId(tableId);
    }

    @ApiOperation(value = "扣减商品库存",notes = "扣减商品库存",httpMethod = "PUT")
    @PutMapping("/deduct-goods-inventory")
    public Boolean deductGoodsInventory(@RequestBody List<DeductGoodsInventory>deductGoodsInventories){
        return goodsService.deductGoodsInventory(deductGoodsInventories);
    }
}
