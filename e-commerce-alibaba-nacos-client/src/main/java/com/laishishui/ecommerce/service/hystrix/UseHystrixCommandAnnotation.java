package com.laishishui.ecommerce.service.hystrix;

import com.laishishui.ecommerce.service.NacosClientService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author tachai on 2022/1/30 6:02 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Service
public class UseHystrixCommandAnnotation {
    private final NacosClientService nacosClientService;

    public UseHystrixCommandAnnotation(NacosClientService nacosClientService) {
        this.nacosClientService = nacosClientService;
    }


    @HystrixCommand(
            // 用于对Hystrix命令进行分组，分组之后便于统计展示仪表盘、上传报告和预警等等
            // 内部进行度量统计时候的分组标识，数据上报和统计的最小维度就是groupKey
            groupKey = "NacosClientService",
            // HystrixCommand的名字，默认是当前类的名字，主要方便Hystrix进行监控，报警
            commandKey = "NacosClientService",
            //舱壁模式
            threadPoolKey = "NacosClientService",
            // 后备模式
            fallbackMethod = "getNacosClentInfoFallback",
            // 断路模式
            commandProperties = {
                    // 超时时间，单位毫秒，超时进fallback
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500"),
                    // 判断熔断的最少请求数，默认是10；只有在一定时间内请求数量达到该值，才会进行成功率的计算
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
                    // 熔断的阀值默认50，表示在一定时间有50%的请求处理失败，会触发熔断
                    @HystrixProperty(name = "circuitBreaker,errorThresholdPercentage",value = "20")
            },
            // 舱壁模式
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value = "30"),
                    @HystrixProperty(name = "maxQueueSize",value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes",value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold",value = "15"),
                    // 在时间窗口中，收集统计信息的次数；在1440ms 的窗口中一共统计 12次
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets",value = "12"),
                    // 在时间窗口，从监听到第一次失败开始计时
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "1500")

            }

    )


    public List<ServiceInstance> getNacosClientInfo(String serviceId){
        log.info("use hystrix command annotation to get nacos client info:[{}],[{}]",
                serviceId,Thread.currentThread().getName());

        return nacosClientService.getNacosClientInfo(serviceId);
    }

    /**
     * <h2>getNacosClientInfo 的兜底策略 - Hystrix后备模式</h2>
     * @param serviceId
     * @return
     */
    public List<ServiceInstance> getNacosClientFallback(String serviceId){
        // 测试 UseHystrixCommandAnnotation 的超时
//        try {
//
//        }catch ()
        log.info("can not get nacos client,trigger hystrix fallback:[{}],[{}]",
                serviceId,Thread.currentThread().getName());

        return Collections.emptyList();
    }
}
