package com.laishihui.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>授权中心鉴权之后给客户端的 Token</h1>
 * Create by tachai on 2021/12/29 9:33 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {

    /** JWT */
    private String token;
}
