package com.laishishui.ecommerce.service.hystrix;

import com.laishishui.ecommerce.service.NacosClientService;
import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE;
import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy.THREAD;

/**
 * <h1>给NacosClientService 实现包装</h1>
 * @author tachai on 2022/1/30 6:54 下午
 * Hystrix 舱壁模式
 * 1. 线程池
 * 2. 信号量：算法+数据结构，有限状态机
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
public class NacosClientHystrixCommand extends HystrixCommand {
   /** 需要保护的服务*/
    private final NacosClientService nacosClientService;
    /** 方法需要传递参数 */
    private final String serviceId;



    public NacosClientHystrixCommand(NacosClientService nacosClientService,String serviceId) {
        super(
                Setter.withGroupKey(
                        HystrixCommandGroupKey.Factory.asKey("NacosClientService"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("NacosClientHystrixCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("NacosClientPool"))
                // 线程池 Key 配置
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(THREAD)// 线程池隔离策略
                        .withFallbackEnabled(true) // 开启降级
                        .withCircuitBreakerEnabled(true)  //开启熔断器
                )
        );

//        //  可以配置信号量隔离策略
//        Setter semaphore = Setter.withGroupKey(
//                HystrixCommandGroupKey.Factory.asKey("NacosClientService"))
//                .andCommandKey(HystrixCommandKey.Factory.asKey("NacosClientHystrixCommand"))
//                .andCommandPropertiesDefaults(
//                        HystrixCommandProperties.Setter()
//                                .withCircuitBreakerRequestVolumeThreshold(10)
//                                .withCircuitBreakerSleepWindowInMilliseconds(5000)
//                                .withCircuitBreakerErrorThresholdPercentage(50)
//                                .withExecutionIsolationStrategy(SEMAPHORE) // 指定用信息量隔离
//                );

        this.nacosClientService=nacosClientService;
        this.serviceId= serviceId;
    }

    /**
     * <h2>要保护的方法调用写在 run 方法中</h2>
     * @return
     * @throws Exception
     */
    @Override
    protected Object run() throws Exception {
        log.info("NacosClientService In Hystrix Command to Get Service Instance:[{}],[{}]",
                this.serviceId,Thread.currentThread().getName());

        return this.nacosClientService.getNacosClientInfo(this.serviceId);
    }

    /**
     * <h2>降级处理策略</h2>
     * @return
     */
    @Override
    protected Object getFallback() {
        log.warn("NacosClientService run error:[{}],[{}]",serviceId,Thread.currentThread().getName());
        return Collections.emptyList();
    }
}
