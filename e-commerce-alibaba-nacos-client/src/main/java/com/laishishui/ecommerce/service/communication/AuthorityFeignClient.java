package com.laishishui.ecommerce.service.communication;

import com.laishihui.ecommerce.vo.JwtToken;
import com.laishihui.ecommerce.vo.UsernameAndPassword;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <h1>与Authority 服务通信的Feign Client接口定义</h1>
 * @author tachai on 2022/1/25 8:24 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@FeignClient(contextId = "AuthorityFeignClient",value = "e-commerce-authority-center")
public interface AuthorityFeignClient {

    /**
     * <h2>通过OpenFeign 访问Authority 获取Token</h2>
     * @param usernameAndPassword
     * @return
     */
    @RequestMapping(value = "/ecommerce-authority-center/authority/token",method = RequestMethod.POST,
    consumes = "application/json",produces = "application/json")
    JwtToken getTokenByFegin(@RequestBody UsernameAndPassword usernameAndPassword);
}
