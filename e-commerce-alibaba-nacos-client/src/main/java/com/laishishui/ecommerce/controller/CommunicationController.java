package com.laishishui.ecommerce.controller;

import com.laishishui.ecommerce.vo.JwtToken;
import com.laishishui.ecommerce.vo.UsernameAndPassword;
import com.laishishui.ecommerce.service.communication.AuthorityFeignClient;
import com.laishishui.ecommerce.service.communication.UseRestTemplateService;
import com.laishishui.ecommerce.service.communication.UseRibbonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>微服务通信Controller</h1>
 * @author tachai on 2022/1/22 10:52 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@RestController
@RequestMapping("/communication")
public class CommunicationController {
    private final AuthorityFeignClient authorityFeignClient;

    private final UseRibbonService useRibbonService;
    private final UseRestTemplateService useRestTemplateService;

    public CommunicationController(UseRestTemplateService useRestTemplateService, UseRibbonService useRibbonService, AuthorityFeignClient authorityFeignClient) {
        this.useRestTemplateService = useRestTemplateService;
        this.useRibbonService = useRibbonService;
        this.authorityFeignClient = authorityFeignClient;
    }

    @PostMapping("/rest-template")
    public JwtToken getTokenFromAuthorityService(@RequestBody UsernameAndPassword usernameAndPassword){
        return useRestTemplateService.getTokenFromAuthorityService(usernameAndPassword);
    }


    @PostMapping("/rest-template-load-balance")
    public JwtToken getTokenFromAuthorityServiceWithLoadBalance(@RequestBody UsernameAndPassword usernameAndPassword){
        return useRestTemplateService.getTokenFromAuthorityServiceWithLoadBalance(usernameAndPassword);
    }

    @PostMapping("/ribbon")
    public JwtToken getTokenFromAuthorityServiceByRibbon(@RequestBody UsernameAndPassword usernameAndPassword){
        return useRibbonService.getTokenFromAuthorityServiceByRibbon(usernameAndPassword);
    }

    @PostMapping("/thinking-in-ribbon")
    public JwtToken thinkingInRibbon(@RequestBody UsernameAndPassword usernameAndPassword){
        return useRibbonService.thinkingInRibbon(usernameAndPassword);
    }

    @PostMapping("/token-by-feign")
    public JwtToken getTokenByFeign(@RequestBody UsernameAndPassword usernameAndPassword){
        return authorityFeignClient.getTokenByFegin(usernameAndPassword);
    }
}
