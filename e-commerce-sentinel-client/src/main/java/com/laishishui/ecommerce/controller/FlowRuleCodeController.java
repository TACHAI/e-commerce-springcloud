package com.laishishui.ecommerce.controller;

/**
 * @author tachai on 2022/6/18 6:57 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.block_handler.LaishishuiBlockHandler;
import com.laishishui.ecommerce.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>流控规则硬编码的 Controller</h1>
 * */
@Slf4j
@RestController
@RequestMapping("/code")
public class FlowRuleCodeController {

    /**
     * <h2>初始化流控规则</h2>
     * */
    @PostConstruct
    public void init() {

        // 流控规则集合
        List<FlowRule> flowRules = new ArrayList<>();
        // 创建流控规则
        FlowRule flowRule = new FlowRule();
        // 设置流控规则 QPS, 限流阈值类型 (QPS, 并发线程数)
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 流量控制手段
        flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        // 设置受保护的资源
        flowRule.setResource("flowRuleCode");
        // 设置受保护的资源的阈值
        flowRule.setCount(1);
        flowRules.add(flowRule);

        // 加载配置好的规则
        FlowRuleManager.loadRules(flowRules);
    }

    /**
     * <h2>采用硬编码限流规则的 Controller 方法</h2>
     * */
    @GetMapping("/flow-rule")
//    @SentinelResource(value = "flowRuleCode")
//    @SentinelResource(value = "flowRuleCode", blockHandler = "handleException")
    @SentinelResource(
            value = "flowRuleCode", blockHandler = "qinyiHandleBlockException",
            blockHandlerClass = LaishishuiBlockHandler.class
    )
    public CommonResponse<String> flowRuleCode() {
        log.info("request flowRuleCode");
        return new CommonResponse<>(0, "", "imooc-qinyi-ecommerce");
    }

    /**
     * <h2>当限流异常抛出时, 指定调用的方法</h2>
     * 是一个兜底策略
     * */
    public CommonResponse<String> handleException(BlockException exception) {
        log.error("has block exception: [{}]", JSON.toJSONString(exception.getRule()));
        return new CommonResponse<>(
                -1,
                "flow rule exception",
                exception.getClass().getCanonicalName()
        );
    }
}
