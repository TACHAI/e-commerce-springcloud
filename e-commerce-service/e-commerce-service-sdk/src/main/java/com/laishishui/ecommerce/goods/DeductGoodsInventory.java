package com.laishishui.ecommerce.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tachai on 2022/1/16 6:54 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@ApiModel(description = "扣减商品库存对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeductGoodsInventory {
    @ApiModelProperty(value = "商品主键 id")
    private Long goodsId;
    @ApiModelProperty(value = "扣减的个数")
    private Integer count;
}
