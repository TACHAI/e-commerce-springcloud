package com.laishishui.ecommerce.converter;

import com.laishishui.ecommerce.constant.BrandCategory;
import com.laishishui.ecommerce.constant.GoodsCategory;

import javax.persistence.AttributeConverter;

/**
 * @author tachai on 2022/1/10 10:17 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class BrandCategoryConverter implements AttributeConverter<BrandCategory,String> {
    @Override
    public String convertToDatabaseColumn(BrandCategory brandCategory) {
        return brandCategory.getCode();
    }

    @Override
    public BrandCategory convertToEntityAttribute(String s) {
        return BrandCategory.of(s);
    }
}
