package com.laishishui.ecommerce.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>简单的商品信息：封面</h1>
 * @author tachai on 2022/1/16 12:03 上午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@ApiModel(description = "简单商品信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleGoodsInfo {

    @ApiModelProperty(value = "商品表主键id")
    private Long id;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "商品图片")
    private String goodsPic;
    @ApiModelProperty(value = "商品价格  单位分")
    private Integer price;

    public SimpleGoodsInfo(Long id){
        this.id=id;
    }
}
