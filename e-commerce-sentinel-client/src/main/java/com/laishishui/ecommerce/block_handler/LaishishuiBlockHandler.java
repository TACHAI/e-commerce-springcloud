package com.laishishui.ecommerce.block_handler;

/**
 * @author tachai on 2022/6/18 6:52 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * <h1>自定义通用的限流处理逻辑</h1>
 * */
@Slf4j
public class LaishishuiBlockHandler {

    /**
     * <h2>通用限流处理方法</h2>
     * 这个方法必须是 static 的
     * */
    public static CommonResponse<String> qinyiHandleBlockException(BlockException exception) {

        log.error("trigger qinyi block handler: [{}], [{}]",
                JSON.toJSONString(exception.getRule()), exception.getRuleLimitApp());
        return new CommonResponse<>(
                -1,
                "flow rule trigger block exception",
                null
        );
    }
}
