package com.laishishui.ecommerce.filter;

import com.laishihui.ecommerce.constant.CommonConstant;
import com.laishihui.ecommerce.util.TokenParseUtil;
import com.laishihui.ecommerce.vo.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>用户身份统一登录拦截</h1>
 * @author tachai on 2022/1/6 2:40 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Component
public class LoginUserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("拦截器执行了：1");


        // 部分请求不需要带有身份信息，即白名单
        if(checkWhiteListUrl(request.getRequestURI())){
            return true;
        }

        // 先尝试从 http header 里面拿到token
        String token = request.getHeader(CommonConstant.JWT_USER_INFO_KEY);
        LoginUserInfo loginUserInfo = null;
        try {
            loginUserInfo = TokenParseUtil.parseUserInfoFromToken(token);
        }catch (Exception ex){
            log.error("parse login userInfo error: [{}]",ex.getMessage(),ex);
        }

        // 如果程序走到这里，说明header 中没有token 信息
        if(null == loginUserInfo){
            throw new RuntimeException("can not parse current login user");
        }

        log.info("set login user info: [{}]",request.getRequestURI());
        // 设置当前请求上下文，把用户信息填充进去
        AccessContext.setLoginUserInfo(loginUserInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * <h2>在请求完全结束后调用，常用于清理资源等工作 或统计请求耗时</h2>
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理 线程耗时
        if(null !=AccessContext.getLoginUserInfo()){
            AccessContext.clearLoginUserInfo();
        }
    }

    /**
     * <h2>校验是否是白名单接口</h2>
     * swagger2 接口
     * @param url
     * @return
     */
    private boolean checkWhiteListUrl(String url){
        return StringUtils.containsAny(
                url,
                "springfox",
                "swagger","v2","webjars","doc.html"
        );
    }
}
