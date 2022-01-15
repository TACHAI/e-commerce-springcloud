package com.laishishui.ecommerce.dao;

import com.laishishui.ecommerce.constant.BrandCategory;
import com.laishishui.ecommerce.constant.GoodsCategory;
import com.laishishui.ecommerce.entity.EcommerceGoods;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * <h1>EcommerceGoods Dao接口定义</h1>
 * @author tachai on 2022/1/10 10:28 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public interface EcommerceGoodsDao extends PagingAndSortingRepository<EcommerceGoods,Long> {

    /**
     * <h2>根据查询条件查询商品表，并限制返回结果</h2>
     * @return
     */
    Optional<EcommerceGoods>findFirstByGoodsCategoryAndBrandCategoryAndGoodsName(
            GoodsCategory goodsCategory,
            BrandCategory brandCategory,
            String goodsName
    );



}
