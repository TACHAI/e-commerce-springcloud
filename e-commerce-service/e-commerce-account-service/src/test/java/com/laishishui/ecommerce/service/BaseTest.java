package com.laishishui.ecommerce.service;

import com.laishishui.ecommerce.vo.LoginUserInfo;
import com.laishishui.ecommerce.filter.AccessContext;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tachai on 2022/1/8 1:58 下午
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class BaseTest {

    protected final LoginUserInfo loginUserInfo = new LoginUserInfo(
            2L,"李四"
    );

    @Before
    public void init(){
        AccessContext.setLoginUserInfo(loginUserInfo);
    }

    @After
    public void destory(){
        AccessContext.clearLoginUserInfo();
    }
}
