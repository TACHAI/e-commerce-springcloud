package com.laishishui.ecommerce.converter;

import com.laishishui.ecommerce.constant.GoodsStatus;

import javax.persistence.AttributeConverter;

/**
 * <h1>商品状态枚举属性转换器</h1>
 * @author tachai on 2022/1/10 9:22 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class GoodsStatusConverter implements AttributeConverter<GoodsStatus,Integer> {

    /**
     * <h2>转换成可以存入数据表的基本类型</h2>
     * @param goodsStatus
     * @return
     */
    @Override
    public Integer convertToDatabaseColumn(GoodsStatus goodsStatus) {
        return goodsStatus.getStatus();
    }

    /**
     * <h2>还原数据表中的字段值到 JAVA 数据类型</h2>
     * @param integer
     * @return
     */
    @Override
    public GoodsStatus convertToEntityAttribute(Integer integer) {
        return GoodsStatus.of(integer);
    }
}
