package com.laishishui.ecommerce.vo;

/**
 * @author tachai on 2022/6/16 7:51 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.laishishui.ecommerce.account.UserAddress;
import com.laishishui.ecommerce.goods.SimpleGoodsInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <h1>订单详情</h1>
 * */
@ApiModel(description = "分页订单详情对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageSimpleOrderDetail {

    @ApiModelProperty(value = "订单详情")
    private List<SingleOrderItem> orderItems;

    @ApiModelProperty(value = "是否有更多的订单(分页)")
    private Boolean hasMore;

    /**
     * <h2>单个订单信息</h2>
     */
    @ApiModel(description = "单个订单信息对象")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleOrderItem {

        @ApiModelProperty(value = "订单表主键 id")
        private Long id;

        @ApiModelProperty(value = "用户地址信息")
        private UserAddress userAddress;

        @ApiModelProperty(value = "订单商品信息")
        private List<SingleOrderGoodsItem> goodsItems;

    }
    @ApiModel(description = "单个订单中的单项商品信息")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleOrderGoodsItem {

        @ApiModelProperty(value = "简单商品信息")
        private SimpleGoodsInfo simpleGoodsInfo;

        @ApiModelProperty(value = "商品个数")
        private Integer count;
    }
}
