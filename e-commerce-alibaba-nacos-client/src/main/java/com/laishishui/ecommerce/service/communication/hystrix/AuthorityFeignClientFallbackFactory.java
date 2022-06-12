package com.laishishui.ecommerce.service.communication.hystrix;

import com.laishishui.ecommerce.vo.JwtToken;
import com.laishishui.ecommerce.vo.UsernameAndPassword;
import com.laishishui.ecommerce.service.communication.AuthorityFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>OpenFeign集成Hystrix 的另一种模式</h1>
 * @author tachai on 2022/1/31 11:17 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Component
public class AuthorityFeignClientFallbackFactory implements FallbackFactory<AuthorityFeignClient> {
    @Override
    public AuthorityFeignClient create(Throwable throwable) {
        log.warn("authority feign client get token by feign request error Hystrix FallbackFactory:[{}]",
                throwable.getMessage(),throwable);
        return new AuthorityFeignClient() {
            @Override
            public JwtToken getTokenByFegin(UsernameAndPassword usernameAndPassword) {
                return new JwtToken("laishishui222");
            }
        };
    }
}
