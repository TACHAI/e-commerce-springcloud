package com.laishishui.ecommerce.sampler;

import brave.sampler.RateLimitingSampler;
import brave.sampler.Sampler;
import org.springframework.cloud.sleuth.sampler.ProbabilityBasedSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>使用配置代码的方式设定抽样率</h1>
 * @author tachai on 2022/1/5 8:39 上午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
//@Configuration   暂不使用
public class SamplerConfig {

    /**
     * <h2>限速采集</h2>
     * @return
     */
    @Bean
    public Sampler sampler(){
        return RateLimitingSampler.create(100);
    }

    /**
     * <h2>概率采集，默认的采样策略，默认值是0.1</h2>
     * @return
     */
    @Bean
    public Sampler defaultSampler(){
        return ProbabilityBasedSampler.create(0.5f);
    }
}
