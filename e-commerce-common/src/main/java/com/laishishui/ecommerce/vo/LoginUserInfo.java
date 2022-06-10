package com.laishishui.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1> 登录用户信息</h1>
 * Create by tachai on 2021/12/29 9:35 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 * @author mac
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserInfo {

    /**  用户id */
    private Long id;

    /** 用户名 */
    private String username;
}
