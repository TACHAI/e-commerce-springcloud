package com.laishishui.ecommerce.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by tachai on 2021/12/26 7:35 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

@Slf4j
@Service
public class NacosClientService {

    private final DiscoveryClient discoveryClient;

    public NacosClientService(DiscoveryClient discoveryClient){
        this.discoveryClient = discoveryClient;
    }


    public List<ServiceInstance>  getNacosClientInfo(String  serviceId){
        log.info("request nacos client to get service instance info: [{}]",serviceId);

        return discoveryClient.getInstances(serviceId);
    }
}
