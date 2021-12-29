package com.laishishui.ecommerce.service;

import com.laishihui.ecommerce.vo.UsernameAndPassword;

/**
 * <h1> JWT相关服务接口定义</h1>
 * @author tachai on 2021/12/29 9:45 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public interface IJWTService {

    /**
     * <h2>生成 JWT Token,使用默认的超时时间</h2>
     * @param username
     * @param password
     * @return
     */
    String generateToken(String username,String password)throws Exception;

    /**
     * <h2>生成指定超时时间Token 单位是天</h2>
     * @param username
     * @param password
     * @param expire
     * @return
     * @throws Exception
     */
    String generateToken(String username,String password,int expire)throws Exception;


    /**
     * <h2>注册用户 同时生成Token返回</h2>
     * @param usernameAndPassword
     * @return
     * @throws Exception
     */
    String registerUserAndGenerateToken(UsernameAndPassword usernameAndPassword)throws Exception;
}
