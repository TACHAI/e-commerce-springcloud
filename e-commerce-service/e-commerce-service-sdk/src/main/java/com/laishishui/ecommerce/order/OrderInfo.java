package com.laishishui.ecommerce.order;

import com.laishishui.ecommerce.goods.DeductGoodsInventory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <h1>订单信息</h1>
 * @author tachai on 2022/6/16 7:40 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@ApiModel(description = "用户发起购买订单")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {

    @ApiModelProperty(value = "用户地址表主键 id")
    private Long userAddress;

    @ApiModelProperty(value = "订单中的商品信息")
    private List<OrderItem> orderItems;

    /**
     * <h2>订单中的商品信息</h2>
     * */
    @ApiModel(description = "订单中的单项商品信息")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {

        @ApiModelProperty(value = "商品表主键 id")
        private Long goodsId;

        @ApiModelProperty(value = "购买商品个数")
        private Integer count;

        public DeductGoodsInventory toDeductGoodsInventory() {
            return new DeductGoodsInventory(this.goodsId, this.count);
        }
    }
}
