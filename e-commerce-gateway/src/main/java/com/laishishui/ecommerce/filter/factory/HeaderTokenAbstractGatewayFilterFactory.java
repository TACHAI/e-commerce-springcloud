package com.laishishui.ecommerce.filter.factory;

import com.laishishui.ecommerce.filter.HeaderTokenGatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.PrefixPathGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author tachai on 2022/1/1 10:45 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Component
public class HeaderTokenAbstractGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    @Override
    public GatewayFilter apply(Object config) {
        return new HeaderTokenGatewayFilter();
    }
}
