package com.laishishui.ecommerce.feign.hystrix;

/**
 * @author tachai on 2022/6/16 8:23 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.common.TableId;
import com.laishishui.ecommerce.feign.SecuredGoodsClient;
import com.laishishui.ecommerce.goods.SimpleGoodsInfo;
import com.laishishui.ecommerce.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * <h1>商品服务熔断降级兜底</h1>
 * */
@Slf4j
@Component
public class GoodsClientHystrix implements SecuredGoodsClient {

    @Override
    public CommonResponse<List<SimpleGoodsInfo>> getSimpleGoodsInfoByTableId(
            TableId tableId) {

        log.error("[goods client feign request error in order service] get simple goods" +
                "error: [{}]", JSON.toJSONString(tableId));
        return new CommonResponse<>(
                -1,
                "[goods client feign request error in order service]",
                Collections.emptyList()
        );
    }
}

