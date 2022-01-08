package com.laishishui.ecommerce.service;

import com.alibaba.fastjson.JSON;
import com.laishishui.ecommerce.account.AddressInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

/**
 * @author tachai on 2022/1/8 2:03 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Slf4j
public class AddressServiceTest extends BaseTest{

    @Autowired
    private IAddressService addressService;

    @Test
    public void testCreateAddressInfo(){
        AddressInfo.AddressItem addressItem = new AddressInfo.AddressItem();
        addressItem.setUsername("laishishui");
        addressItem.setPhone("120110119");
        addressItem.setProvince("上海市");
        addressItem.setCity("上海市");
        addressItem.setAddressDetail("北蔡镇");

        log.info("test create address info:[{}]", JSON.toJSONString(
              addressService.createAddressInfo(
                      new AddressInfo(
                              loginUserInfo.getId(), Collections.singletonList(addressItem)
                      )
              )
        ));
    }
}
