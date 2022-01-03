package com.laishishui.ecommerce.service;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tachai on 2022/1/3 2:15 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
@Service
public class SleuthTraceInfoService {

    /** brave.Tracer 跟踪对象*/
    private final Tracer tracer;

    public SleuthTraceInfoService(Tracer tracer) {
        this.tracer = tracer;
    }

    /**
     * <h2>打印当前的跟踪信息到日志中</h2>
     */
    public void logCurrentTraceInfo(){
        log.info("Sleuth trace id: [{}]",tracer.currentSpan().context().traceIdString());
        log.info("Sleuth span id: [{}]",tracer.currentSpan().context().spanId());
    }
}
