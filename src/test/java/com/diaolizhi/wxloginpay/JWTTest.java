package com.diaolizhi.wxloginpay;

import com.diaolizhi.wxloginpay.domain.User;
import com.diaolizhi.wxloginpay.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * @program: wx-login-pay
 * @description:
 * @author: diaolizhi
 * @create: 2019-07-18 22:37
 **/
public class JWTTest {

    @Test
    public void test() {
        Claims claims = JWTUtils.checkJWT("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYmRkamdqYSIsImlkIjoxMSwidXNlcm5hbWUiOiLlvKDkuIkiLCJoZWFkSW1nIjoiaHR0cDovL2EuY29tL2oucG5nIiwiaWF0IjoxNTYzNDYwOTU2LCJleHAiOjE1NjQwNjU3NTZ9.KUpJR7hpPREiZGoHUyTrKaevNKGaGTVYJsBZ1PWtRGc");
        String username = (String) claims.get("username");
        System.out.println(username);
        String headImg = (String) claims.get("headImg");
        System.out.println(headImg);
    }

    @Test
    public void test1() {
        User user = new User();
        user.setId(11);
        user.setUsername("张三");
        user.setHeadImg("http://a.com/j.png");
        String token = JWTUtils.geneToken(user);
        System.out.println(token);
    }
}
