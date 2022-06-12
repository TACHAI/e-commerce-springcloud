package com.laishishui.ecommerce.service.communication.hystrix;

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.vo.JwtToken;
import com.laishishui.ecommerce.vo.UsernameAndPassword;
import com.laishishui.ecommerce.service.communication.AuthorityFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>AuthorityFeignClient 后备fallback</h1>
 * @author tachai on 2022/1/31 11:10 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Component
public class AuthorityFeignClientFallback implements AuthorityFeignClient {
    @Override
    public JwtToken getTokenByFegin(UsernameAndPassword usernameAndPassword) {

        log.info("authority feign client get token by feign request error Hystrix Fallback:[{}]," +
                JSON.toJSONString(usernameAndPassword));
        return new JwtToken("laishishui1111");
    }
}
