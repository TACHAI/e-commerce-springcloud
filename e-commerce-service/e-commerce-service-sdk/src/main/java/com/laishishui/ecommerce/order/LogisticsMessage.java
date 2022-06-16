package com.laishishui.ecommerce.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tachai on 2022/6/16 7:43 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@ApiModel(description = "Stream 物流消息对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsMessage {

    @ApiModelProperty(value = "用户表主键 id")
    private Long userId;

    @ApiModelProperty(value = "订单表主键 id")
    private Long orderId;

    @ApiModelProperty(value = "用户地址表主键 id")
    private Long addressId;

    @ApiModelProperty(value = "备注信息(json 存储)")
    private String extraInfo;

}
