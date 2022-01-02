package com.laishishui.ecommerce.filter;

import com.alibaba.fastjson.JSON;
import com.laishihui.ecommerce.constant.CommonConstant;
import com.laishihui.ecommerce.util.TokenParseUtil;
import com.laishihui.ecommerce.vo.JwtToken;
import com.laishihui.ecommerce.vo.LoginUserInfo;
import com.laishihui.ecommerce.vo.UsernameAndPassword;
import com.laishishui.ecommerce.constant.GatewayConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <h1>全局登录鉴权过滤器</h1>
 * @author tachai on 2022/1/2 7:48 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

@Slf4j
@Component
public class GloballoginOrRegisterFilter implements GlobalFilter, Ordered {
    /** 注册中心客户端的实例信息*/
    private final LoadBalancerClient loadBalancerClient;

    private final RestTemplate restTemplate;

    public GloballoginOrRegisterFilter(LoadBalancerClient loadBalancerClient, RestTemplate restTemplate) {
        this.loadBalancerClient = loadBalancerClient;
        this.restTemplate = restTemplate;
    }

    /**
     * <h2>登录、注册、鉴权</h2>
     * 1. 如果的登录或注册，则去授权中心拿到Token 并返回给客户端
     * 2. 如果是访问其他的服务，则鉴权，没有权限返回 401
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 1.如果是登录
        if(request.getURI().getPath().contains(GatewayConstant.LOGIN_URI)){
            // 去授权中心拿 token
            String token = getTokenFromAuthorityCenter(
                    request,GatewayConstant.AUTHORITY_CENTER_TOKEN_URL_FORMAT
            );
            // header 中不能设置null
            response.getHeaders().add(
                    CommonConstant.JWT_USER_INFO_KEY,
                    null==token?"null":token);
            response.setStatusCode(HttpStatus.OK);
            return response.setComplete();
        }
        // 2. 如果是注册
        if(request.getURI().getPath().contains(GatewayConstant.REGISTER_URI)){
            // 去授权中心拿 token: 先创建用户，再返回Token
            String token = getTokenFromAuthorityCenter(
                    request,GatewayConstant.AUTHORITY_CENTER_REGISTER_URL_FORMAT
            );
            // header 中不能设置null
            response.getHeaders().add(
                    CommonConstant.JWT_USER_INFO_KEY,
                    null==token?"null":token);
            response.setStatusCode(HttpStatus.OK);
            return response.setComplete();
        }
        // 3. 访问其他的服务，则鉴权，校验是否能够从Token 中解析出用户信息
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(CommonConstant.JWT_USER_INFO_KEY);
        LoginUserInfo loginUserInfo = null;
        try {
            loginUserInfo = TokenParseUtil.parseUserInfoFromToken(token);
        }catch (Exception ex){
            log.error("parse user info from token error: [{}]",ex.getMessage());
        }
        // 获取不到登录用户信息，返回401
        if(null==loginUserInfo){
             response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // 解析通过，则方行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+2;
    }

    /**
     * <h2>从授权中心获取Token</h2>
     * @param request
     * @param uriFormat
     * @return
     */
    private String getTokenFromAuthorityCenter(ServerHttpRequest request,String uriFormat){

        // service id 就是服务名字，负载均衡
        ServiceInstance serviceInstance = loadBalancerClient.choose(
                CommonConstant.AUTHORITY_CENTER_SERVICE_ID);

        log.info("Nacos Client Info: [{}],[{}],[{}]",
                serviceInstance.getServiceId(),serviceInstance.getInstanceId(),
                JSON.toJSONString(serviceInstance.getMetadata()));
        String requestUrl = String.format(
                uriFormat,serviceInstance.getHost(),serviceInstance.getPort()
        );

        UsernameAndPassword requestBody= JSON.parseObject(
                parseBodyFromRequest(request),UsernameAndPassword.class
        );
        log.info("login request url and body: [{}],[{}]",requestUrl,
                JSON.toJSONString(requestBody));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JwtToken token = restTemplate.postForObject(
                requestUrl,
                new HttpEntity<>(JSON.toJSONString(requestBody),headers),
                JwtToken.class
        );

        if(null!=token){
            return token.getToken();
        }
        return null;
    }

    /**
     * <h2>从Post 请求中获取请求数据</h2>
     * @param request
     * @return
     */
    private String parseBodyFromRequest(ServerHttpRequest request){
        // 获取请求体
        Flux<DataBuffer> body = request.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();

        // 订阅缓冲区去消费请求体中的数据
        body.subscribe(buffer->{
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            // 一定要使用DataBufferUtils .release 释放掉，否则会引起内存泄漏
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });

        // 获取request body
        return bodyRef.get();
    }
}
