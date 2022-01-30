package com.laishishui.ecommerce.controller;

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.service.NacosClientService;
import com.laishishui.ecommerce.service.hystrix.NacosClientHystrixCommand;
import com.laishishui.ecommerce.service.hystrix.NacosClientHystrixObservableCommand;
import com.laishishui.ecommerce.service.hystrix.UseHystrixCommandAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author tachai on 2022/1/30 6:38 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/hystrix")
public class HystrixController {
    private final NacosClientService nacosClientService;
    private final UseHystrixCommandAnnotation hystrixCommandAnnotation;

    public HystrixController(UseHystrixCommandAnnotation hystrixCommandAnnotation, NacosClientService nacosClientService) {
        this.hystrixCommandAnnotation = hystrixCommandAnnotation;
        this.nacosClientService = nacosClientService;
    }

    @GetMapping("/hystrix-command-annotation")
    public List<ServiceInstance> getNacosClientInfoUseAnnotation(@RequestParam String serviceId){
        log.info("request nacos client info use annotation: [{}],[{}]",serviceId,Thread.currentThread().getName());
        return hystrixCommandAnnotation.getNacosClientInfo(serviceId);
    }
    @GetMapping("/simple-hystrix-command")
    public List<ServiceInstance> getServiceInstanceByServieId(@RequestParam String  seviceId) throws ExecutionException, InterruptedException {

        // 第一种方式
        List<ServiceInstance> serviceInstances01 = (List<ServiceInstance>) new NacosClientHystrixCommand(
                nacosClientService,seviceId
        ).execute();// 同步阻塞

        log.info("use execute to get service instance:[{}],[{}]",
                JSON.toJSONString(serviceInstances01),Thread.currentThread().getName());
        // 第二种方式
        List<ServiceInstance> serviceInstances02;
        Future<List<ServiceInstance>> future= new NacosClientHystrixCommand(
                nacosClientService,seviceId
        ).queue();// 异步非阻塞

        // 这里可以做一些别的事，需要的时候再去拿结果
        serviceInstances02 = future.get();
        log.info("use future to get service instance:[{}],[{}]",
                JSON.toJSONString(serviceInstances02),Thread.currentThread().getName());

        // 第三种方式
        Observable<List<ServiceInstance>> observable = new NacosClientHystrixCommand(
                nacosClientService,seviceId
        ).observe(); // 热响应调用
        List<ServiceInstance> serviceInstances03 = observable.toBlocking().single();
        log.info("use observable to get service instance:[{}],[{}]",
                JSON.toJSONString(serviceInstances03),Thread.currentThread().getName());
        // 第四种方式
        Observable<List<ServiceInstance>> toObservable = new NacosClientHystrixCommand(
                nacosClientService,seviceId
        ).toObservable(); // 异步冷响应调用
        List<ServiceInstance> serviceInstances04 = observable.toBlocking().single();
        log.info("use toObservable to get service instance:[{}],[{}]",
                JSON.toJSONString(serviceInstances04),Thread.currentThread().getName());
        // execute = queue + get
        return serviceInstances01;

    }

    @GetMapping("/hsytrix-observable-command")
    public List<ServiceInstance> getServiceInstanceByServiceIdObservable(@RequestParam String serviceId){
        List<String> serviceIds = Arrays.asList(serviceId,serviceId,serviceId);
        List<List<ServiceInstance>> result = new ArrayList<>(serviceIds.size());

        NacosClientHystrixObservableCommand observableCommand = new NacosClientHystrixObservableCommand(
                nacosClientService,serviceIds);
        // 异步执行命令
        Observable<List<ServiceInstance>> observe=observableCommand.observe();

        // 注册获取结果
        observe.subscribe(
                new Observer<List<ServiceInstance>>() {
                    // 执行 onNext 之后再去执行onCompleted
                    @Override
                    public void onCompleted() {
                        log.info("all tasks is complete:[{}],[{}]",serviceId,Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<ServiceInstance> serviceInstances) {
                        result.add(serviceInstances);
                    }
                }
        );

        log.info("obserable command result is:[{}],[{}]",JSON.toJSONString(result),Thread.currentThread().getName());
        return result.get(0);
    }
}
