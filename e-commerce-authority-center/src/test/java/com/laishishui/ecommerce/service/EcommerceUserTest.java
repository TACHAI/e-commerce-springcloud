package com.laishishui.ecommerce.service;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.dao.EcommerceUserDao;
import com.laishishui.ecommerce.entity.EcommerceUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * <h1>EcommerceUser 相关的测试</h1>
 * Create by tachai on 2021/12/29 8:51 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EcommerceUserTest {


    @Autowired
    private EcommerceUserDao ecommerceUserDao;

    @Test
    public void createUserRecord(){
        EcommerceUser user = new EcommerceUser();
        user.setUsername("张三");
        user.setPassword(MD5.create().digestHex("123456"));
        user.setExtraInfo("{}");
        log.info("save user: [{}]", JSON.toJSON(ecommerceUserDao.save(user)));
    }

    @Test
    public void findByUsername(){
        EcommerceUser user=ecommerceUserDao.findByUsername("张三");
        log.info("find user:[{}]", JSON.toJSON(user));
    }
}
