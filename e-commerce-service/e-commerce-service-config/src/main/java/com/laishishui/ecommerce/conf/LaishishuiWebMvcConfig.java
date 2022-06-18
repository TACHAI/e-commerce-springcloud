package com.laishishui.ecommerce.conf;

import com.alibaba.cloud.seata.web.SeataHandlerInterceptor;
import com.laishishui.ecommerce.filter.LoginUserInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * <h1>Web Mvc 配置</h1>
 * 配置拦截器不会生效 ，要做到mvc  配置里面
 * @author tachai on 2022/1/6 2:51 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

@Configuration
public class LaishishuiWebMvcConfig extends WebMvcConfigurationSupport {


    /**
     * <h2> 添加拦截器配置</h2>
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 添加用户身份统一登录拦截的拦截器
        registry.addInterceptor(new LoginUserInfoInterceptor())
                .addPathPatterns("/**").order(0);
        // Seata 传递 xid 事务 id 给其他的微服务
        // 只有这样, 其他的服务才会写 undo_log, 才能够实现回滚
        registry.addInterceptor(new SeataHandlerInterceptor()).addPathPatterns("/**");
    }

    /**
     * <h2>让MVC 加载 Swagger 的静态资源</h2>
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resource");
//        registry.addResourceHandler("doc.html")
//                .addResourceLocations("classpath:/META-INF/resource");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resource/webjars");


        registry.addResourceHandler("/**").
                addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");


        super.addResourceHandlers(registry);
    }
}
