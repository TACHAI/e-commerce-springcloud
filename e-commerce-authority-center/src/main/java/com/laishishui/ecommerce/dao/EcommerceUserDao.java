package com.laishishui.ecommerce.dao;

import com.laishishui.ecommerce.entity.EcommerceUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1> EcommerceUserDao 接口定义</h1>
 * Create by tachai on 2021/12/29 8:41 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public interface EcommerceUserDao extends JpaRepository<EcommerceUser,Long> {
    /**
     * <h2> 根据用户名查询 EcommerceUser 对象</h2>
     * select * from t_ecommerce_user where username = ?
     * @param username
     * @return
     */
    EcommerceUser findByUsername(String username);


    /**
     * <h2>根据用户名和密码查询实体对象</h2>
     * select * from t_ecommerce_user where username = ? and password = ?
     * @param username
     * @param password
     * @return
     */
    EcommerceUser findByUsernameAndPassword(String username,String password);
}
