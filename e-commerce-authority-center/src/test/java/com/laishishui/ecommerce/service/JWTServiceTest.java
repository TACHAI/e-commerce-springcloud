package com.laishishui.ecommerce.service;

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.util.TokenParseUtil;
import com.laishishui.ecommerce.vo.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tachai on 2021/12/30 3:53 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTServiceTest {

    @Autowired
    private IJWTService ijwtService;
    @Test
    public void testGenerateAndPareToken()throws Exception{
        String jwtToKen = ijwtService.generateToken(
                "张三",
                "e10adc3949ba59abbe56e057f20f883e"
        );
        log.info("jwt token is: [{}]",jwtToKen);

        LoginUserInfo userInfo = TokenParseUtil.parseUserInfoFromToken(jwtToKen);
        log.info("parse token: [{}]", JSON.toJSONString(userInfo));
    }

}
