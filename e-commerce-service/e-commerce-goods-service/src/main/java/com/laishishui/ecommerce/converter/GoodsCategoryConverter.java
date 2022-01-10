package com.laishishui.ecommerce.converter;

import com.laishishui.ecommerce.constant.GoodsCategory;

import javax.persistence.AttributeConverter;

/**
 * <h1>商品类别枚举属性转换器</h1>
 * @author tachai on 2022/1/10 9:46 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class GoodsCategoryConverter implements AttributeConverter<GoodsCategory,String> {
    @Override
    public String convertToDatabaseColumn(GoodsCategory goodsCategory) {
        return goodsCategory.getCode();
    }

    @Override
    public GoodsCategory convertToEntityAttribute(String s) {
        return GoodsCategory.of(s);
    }
}
