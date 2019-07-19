package com.diaolizhi.wxloginpay;

import com.diaolizhi.wxloginpay.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: wx-login-pay
 * @description: 获取 access_token 测试
 * @author: diaolizhi
 * @create: 2019-07-18 20:35
 **/

/*
* 021L3Pp62N7DpS0gPum62ee3q62L3Ppx
23__OLpA-iapLMM612-En10X6vEwoJV4Svn0XWtAd6qv5ehtgcd7Upz2OgiBEfJMbserPKsuhsK1-xT24gKOePOvLECYBZUfkh-B-cl8awjMAg
*
* */

@RunWith(SpringRunner.class) // 底层用 JUnit
@SpringBootTest(classes = {WxLoginPayApplication.class}) // 启动整个 SpringBoot 项目
public class GetAccessTokenTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.saveUser("001rvCTP0R3WK32SUcUP0fFNTP0rvCT8");
    }


}
