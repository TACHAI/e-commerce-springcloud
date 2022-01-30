package com.laishishui.ecommerce.service.hystrix;

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.service.NacosClientService;
import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import rx.Observable;
import rx.Subscriber;

import java.util.Collections;
import java.util.List;

/**
 * <h1>HystrixCommand,隔离策略是基于信息量实现的</h1>
 * @author tachai on 2022/1/30 7:56 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
public class NacosClientHystrixObservableCommand
        extends HystrixObservableCommand<List<ServiceInstance>> {
    /**要保护的服务 */
    private final NacosClientService nacosClientService;
    /** 方法需要传递的参数 */
    private final List<String> serviceIds;

    public NacosClientHystrixObservableCommand(NacosClientService nacosClientService,List<String> serviceIds) {
        super(
                HystrixObservableCommand.Setter.withGroupKey(
                        HystrixCommandGroupKey.Factory.asKey("NacosClientService")
                )
                .andCommandKey(HystrixCommandKey.Factory.asKey("NacosClientHystrixObservableCommand"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                        .withFallbackEnabled(true)//开启降级
                        .withCircuitBreakerEnabled(true) // 开启熔断器
                )
        );
        this.nacosClientService=nacosClientService;
        this.serviceIds=serviceIds;
    }

    /**
     * <h2>要保护的方法调用写在这里</h2>
     * @return
     */
    @Override
    protected Observable<List<ServiceInstance>> construct() {
        return Observable.create(new Observable.OnSubscribe<List<ServiceInstance>>() {
            // Observable 有三个关键的事件方法，分别是onNext、onCompleted、onError
            @Override
            public void call(Subscriber<? super List<ServiceInstance>> subscriber) {
                try {
                    if(!subscriber.isUnsubscribed()){
                        log.info("subscriberr command task:[{}],[{}]",
                                JSON.toJSONString(serviceIds),
                                Thread.currentThread().getName()
                                );

                        serviceIds.forEach(s->subscriber.onNext(nacosClientService.getNacosClientInfo(s)));
                    }
                    subscriber.onCompleted();
                    log.info("command task complete:[{}],[{}]",JSON.toJSONString(serviceIds),Thread.currentThread().getName());
                }catch (Exception ex){
                    subscriber.onError(ex);
                }
            }
        });
    }

    /**
     * <h2>服务降级策略</h2>
     * @return
     */
    @Override
    protected Observable<List<ServiceInstance>> resumeWithFallback() {
        return Observable.create(new Observable.OnSubscribe<List<ServiceInstance>>() {
            // Observable 有三个关键的事件方法，分别是onNext、onCompleted、onError
            @Override
            public void call(Subscriber<? super List<ServiceInstance>> subscriber) {
                try {
                    if(!subscriber.isUnsubscribed()){
                        log.info("subscriberr command task:[{}],[{}]",
                                JSON.toJSONString(serviceIds),
                                Thread.currentThread().getName()
                        );
                        subscriber.onNext(Collections.emptyList());
                        subscriber.onCompleted();
                        log.info("command task complete:[{}],[{}]",JSON.toJSONString(serviceIds),Thread.currentThread().getName());
                    }
                }catch (Exception ex){
                    subscriber.onError(ex);
                }
            }
        });    }
}
