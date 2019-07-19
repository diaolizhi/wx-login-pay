package com.diaolizhi.wxloginpay;

import com.diaolizhi.wxloginpay.domain.User;
import com.diaolizhi.wxloginpay.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: wx-login-pay
 * @description:
 * @author: diaolizhi
 * @create: 2019-07-18 18:32
 **/

@RunWith(SpringRunner.class) // 底层用 JUnit
@SpringBootTest(classes = {WxLoginPayApplication.class}) // 启动整个 SpringBoot 项目
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("张三");
        user.setOpenid("123abc");
        user.setHeadImg("http://a.com/j.png");
        int res = userMapper.addUser(user);
        assert res == 1;
    }

    @Test
    public void test1() {
        User user = userMapper.findUserById(2);
        System.out.println(user);
    }

    @Test
    public void test2() {
        User user = userMapper.findUserByOpenid("123abc");
        System.out.println(user);
    }


}
