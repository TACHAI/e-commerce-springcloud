package com.laishishui.ecommerce.conf;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.savedrequest.SavedRequest;

/**
 * <h1>配置安全认证，以便其他的微服务可以注册</h1>
 * Create by tachai on 2021/12/27 8:08 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

    /** 应用上下文路径*/
    private final String adminContextPath;

    public SecuritySecureConfig (AdminServerProperties adminServerProperties){
        this.adminContextPath=adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

       SavedRequestAwareAuthenticationSuccessHandler successHandler=new SavedRequestAwareAuthenticationSuccessHandler();;
       successHandler.setTargetUrlParameter("redirectTo");
       successHandler.setDefaultTargetUrl(adminContextPath+"/");

        http.authorizeRequests()
                // 1. 配置所有的静态资源和登录也以公开访问
                .antMatchers(adminContextPath+"/assets/**").permitAll()
                .antMatchers(adminContextPath+"/login").permitAll()
                // 2. 其他请求，必须要经过认证
                .anyRequest().authenticated()
                .and()
                // 3.配置登录和登出路径
                .formLogin().loginPage(adminContextPath+"/login").successHandler(successHandler)
                .and()
                .logout().logoutUrl(adminContextPath+"/logout")
                .and()
                // 4. 开启http basic 支持，其他的服务模块注册是需要使用
                .httpBasic()
                .and()
                // 5. 开启基于 cookie的csrf 保护
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                // 6. 忽略这些路径的 csrf 保护以使其他的模块可以实现注册
                .ignoringAntMatchers(
                        adminContextPath+"/instances",
                        adminContextPath+"/actuator/**"
                );

        //super.configure(http);
    }
}
