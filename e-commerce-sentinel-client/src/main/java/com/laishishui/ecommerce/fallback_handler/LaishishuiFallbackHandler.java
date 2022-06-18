package com.laishishui.ecommerce.fallback_handler;

/**
 * @author tachai on 2022/6/18 6:51 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.vo.JwtToken;
import com.laishishui.ecommerce.vo.UsernameAndPassword;
import lombok.extern.slf4j.Slf4j;

/**
 * <h1>Sentinel 回退降级的兜底策略</h1>
 * 都需要是静态方法
 * */
@Slf4j
public class LaishishuiFallbackHandler {
    /**
     * <h2>getTokenFromAuthorityService 方法的 fallback</h2>
     * */
    public static JwtToken getTokenFromAuthorityServiceFallback(
            UsernameAndPassword usernameAndPassword
    ) {
        log.error("get token from authority service fallback: [{}]",
                JSON.toJSONString(usernameAndPassword));
        return new JwtToken("imooc-qinyi-fallback");
    }

    /**
     * <h2>ignoreException 方法的 fallback</h2>
     * */
    public static JwtToken ignoreExceptionFallback(Integer code) {
        log.error("ignore exception input code: [{}] has trigger exception", code);
        return new JwtToken("imooc-qinyi-fallback");
    }
}
