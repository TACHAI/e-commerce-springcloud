package com.laishihui.ecommerce.vo;

/**
 * Create by tachai on 2021/12/25 9:21 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <h1>通用响应对象定义</h1>
 * {
 *     "code": 0,
 *     "message": "",
 *     "data": {}
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {
    /** 错误码 */
    private Integer code;

    /** 错误消息 */
    private String message;

    /** 泛型响应数据 */
    private T Data;

    public CommonResponse(Integer code,String message){
        this.code=code;
        this.message=message;
    }

}
