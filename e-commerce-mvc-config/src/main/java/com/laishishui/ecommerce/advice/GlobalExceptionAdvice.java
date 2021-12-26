package com.laishishui.ecommerce.advice;

/**
 * Create by tachai on 2021/12/25 9:54 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.laishihui.ecommerce.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <h2>全局异常捕获处理</h2>
 * */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {


    @ExceptionHandler(value = Exception.class)
    public CommonResponse<String> handlerCommerException(
            HttpServletRequest req,Exception ex
    ){
        CommonResponse<String> response = new CommonResponse<>(
                -1,"business error"
        );
        response.setData(ex.getMessage());
        log.error("commerce service has error: [{}]",ex.getMessage(),ex);
        return response;
    }
}
