package com.laishishui.ecommerce.service.hystrix;

import com.laishishui.ecommerce.service.NacosClientService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>使用注解方式 缓存 请求</h1>
 * @author tachai on 2022/1/30 9:24 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Service
public class CacheHystrixCommandAnnotation {

    private final NacosClientService nacosClientService;

    public CacheHystrixCommandAnnotation(NacosClientService nacosClientService) {
        this.nacosClientService = nacosClientService;
    }

    // 第一种 Hystrix Cache 注解的使用方法
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(commandKey = "CacheHystrixCommandAnnotation")
    public List<ServiceInstance> useCacheByAnnotation01(String serviceId){
        log.info("use cache01 to get nacos  client info:[{}]",serviceId);
        return nacosClientService.getNacosClientInfo(serviceId);
    }

    @CacheRemove(commandKey = "CacheHystrixCommandAnnotation",cacheKeyMethod = "getCacheKey")
    public void flushCacheByAnnotation(String cacheId){
       log.info("flush hystrix cache key: [{}]",cacheId);
    }

    public String getCacheKey(String cacheId){
        return cacheId;
    }



    // 第二种Hystrix Cache 注解的使用方法  最常用
    @CacheResult
    @HystrixCommand(commandKey = "CacheHystrixCommandAnnotation")
    public List<ServiceInstance> useCacheByAnnotation02(@CacheKey String serviceId){
        log.info("use cache02 to get nacosClient info:[{}]",serviceId);
        return nacosClientService.getNacosClientInfo(serviceId);
    }

    @CacheRemove(commandKey = "CacheHystrixCommandAnnotation",cacheKeyMethod = "getCacheKey")
    public void flushCacheByAnnotation02(@CacheKey String cacheId){
        log.info("flush hystrix cache key: [{}]",cacheId);
    }

    // 第三种 Hystrix Cache 注解的使用方法

    @CacheResult
    @HystrixCommand(commandKey = "CacheHystrixCommandAnnotation")
    public List<ServiceInstance> useCacheByAnnotation03( String serviceId){
        log.info("use cache03 to get nacosClient info:[{}]",serviceId);
        return nacosClientService.getNacosClientInfo(serviceId);
    }

    @CacheRemove(commandKey = "CacheHystrixCommandAnnotation",cacheKeyMethod = "getCacheKey")
    public void flushCacheByAnnotation03( String cacheId){
        log.info("flush hystrix cache key: [{}]",cacheId);
    }
}
