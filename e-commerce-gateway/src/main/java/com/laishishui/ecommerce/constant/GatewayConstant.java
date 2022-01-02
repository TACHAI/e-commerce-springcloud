package com.laishishui.ecommerce.constant;

/**
 * <h1>网关常量定义</h1>
 * @author tachai on 2022/1/2 7:20 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class GatewayConstant {
    /** 登录 uri **/
    public static final String LOGIN_URI ="/e-commerce/login/";

    /** 注册 uri **/
    public static final String REGISTER_URI="/e-commerce/register/";
    /** 去授权中心拿到登录token 的uri格式化接口*/
    public static final String AUTHORITY_CENTER_TOKEN_URL_FORMAT =
            "http://%s:%s/ecommerce-authority-center/authority/token";

    /** 去授权中心注册并拿到token 的uri格式化接口*/
    public static final String AUTHORITY_CENTER_REGISTER_URL_FORMAT =
            "http://%s:%s/ecommerce-authority-center/authority/register";
}
