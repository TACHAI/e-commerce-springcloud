package com.laishishui.ecommerce.controller;

import com.laishishui.ecommerce.service.NacosClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create by tachai on 2021/12/26 7:42 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

@Slf4j
@RestController
@RequestMapping("/nacos-client")
public class NacosClientController {

    private final NacosClientService nacosClientService;

    public NacosClientController(NacosClientService nacosClientService){
        this.nacosClientService = nacosClientService;
    }

    /**
     * <h2>根据 service id 获取服务所有的实例信息</h2>
     * @param serverId
     * @return
     */
    @GetMapping("/service-instance")
    public List<ServiceInstance> logNacosClientInfo(
            @RequestParam(defaultValue = "e-commerce-nacos-client")String serverId)
    {
        log.info("coming in log nacos client info:{}",serverId);
        return nacosClientService.getNacosClientInfo(serverId);
    }
}
