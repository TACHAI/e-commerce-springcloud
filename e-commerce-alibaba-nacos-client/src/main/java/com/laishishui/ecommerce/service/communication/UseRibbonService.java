package com.laishishui.ecommerce.service.communication;

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.constant.CommonConstant;
import com.laishishui.ecommerce.vo.JwtToken;
import com.laishishui.ecommerce.vo.UsernameAndPassword;
import com.netflix.loadbalancer.*;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>使用Ribbon方式调用服务</h2>
 * @author tachai on 2022/1/24 9:30 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Service
public class UseRibbonService {
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    public UseRibbonService(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    /**
     * <h2>通过 Ribbon调用Authority 服务获取Token</h2>
     * @return
     */
    public JwtToken getTokenFromAuthorityServiceByRibbon(UsernameAndPassword usernameAndPassword){
        // 注意url中的ip 和端口换成了服务名称
        String requestUrl = String.format(
                "http://%s/ecommerce-authority-center/authority/token",
                CommonConstant.AUTHORITY_CENTER_SERVICE_ID
        );
        log.info("login request url and body:[{}],[{}]",requestUrl,JSON.toJSONString(usernameAndPassword));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 这里一定要使用自己注入的RestTemplate
        return restTemplate.postForObject(
                requestUrl,
                new HttpEntity<>(JSON.toJSONString(usernameAndPassword),headers),
                JwtToken.class
        );

    }

    /**
     * <h2>使用原生的Ribbon Api,看看Ribbon是如何完成：服务调用+负载均衡 </h2>
     * @param usernameAndPassword
     * @return
     */
    public JwtToken thinkingInRibbon(UsernameAndPassword usernameAndPassword){
        String urlFormat = "http://%s/ecommerce-authority-center/authority/token";
        //1.找到服务提供方的地址和端口
        List<ServiceInstance> targetInstances = discoveryClient.getInstances(
                CommonConstant.AUTHORITY_CENTER_SERVICE_ID

        );
        //构建Ribbon 服务列表
        List<Server> servers = new ArrayList<>(targetInstances.size());
        targetInstances.forEach(i->{
            servers.add(new Server(i.getHost(),i.getPort()));
            log.info("found target instance: [{}]->[{}]",i.getHost(),i.getPort());
        });
        // 2.使用负载均衡策略实现远程服务调用

        // 构建Ribbon负载实例
        BaseLoadBalancer  loadBalancer = LoadBalancerBuilder.newBuilder()
                .buildFixedServerListLoadBalancer(servers);

        //设置负载均衡策略
        loadBalancer.setRule(new RetryRule(new RandomRule(),300));

        String result = LoadBalancerCommand.builder().withLoadBalancer(loadBalancer)
                .build().submit(server -> {
                    String targetUrl=String.format(
                            urlFormat,
                            String.format("%s:%s",server.getHost(),server.getPort())
                    );
                    log.info("target request url:[{}]",targetUrl);
                    HttpHeaders headers  = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    String tokenStr = new RestTemplate().postForObject(
                            targetUrl,
                            new HttpEntity<>(JSON.toJSONString(usernameAndPassword),headers),
                            String.class
                    );

                    return Observable.just(tokenStr);
                }).toBlocking().first().toString();

        return JSON.parseObject(result,JwtToken.class);
    }
}
