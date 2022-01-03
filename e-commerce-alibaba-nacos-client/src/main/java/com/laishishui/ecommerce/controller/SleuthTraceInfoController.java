package com.laishishui.ecommerce.controller;

import com.laishishui.ecommerce.service.SleuthTraceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>打印跟踪信息</h1>
 * @author tachai on 2022/1/3 2:22 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/sleuth")
public class SleuthTraceInfoController {
    private final SleuthTraceInfoService traceInfoService;

    public SleuthTraceInfoController(SleuthTraceInfoService traceInfoService) {
        this.traceInfoService = traceInfoService;
    }

    /**
     * <h2>打印日志跟踪信息</h2>
     */
    @GetMapping("/trace-info")
    public void logCurrentTraceInfo(){
        traceInfoService.logCurrentTraceInfo();
    }
}
