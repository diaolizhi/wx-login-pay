package com.diaolizhi.wxloginpay.service.impl;

import com.diaolizhi.wxloginpay.config.WXConfig;
import com.diaolizhi.wxloginpay.domain.User;
import com.diaolizhi.wxloginpay.mapper.UserMapper;
import com.diaolizhi.wxloginpay.service.UserService;
import com.diaolizhi.wxloginpay.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @program: wx-login-pay
 * @description:
 * @author: diaolizhi
 * @create: 2019-07-18 20:26
 **/

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private WXConfig wxConfig;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveUser(String code) {
        System.out.println(code);
        Map<String, String> map = getAccessTokenByCode(code);
        Map<String, String> userinfo = getUserInfoByAccessToken(map.get("access_token"), map.get("openid"));

        User user = userMapper.findUserByOpenid(userinfo.get("openid"));

        if (user != null) {
            System.out.println("该用户已经存在了");
            return user;
        }

        user = new User();
        try {
            user.setOpenid(new String(userinfo.get("openid").getBytes("ISO-8859-1"), "UTF-8"));
            user.setUsername(new String(userinfo.get("nickname").getBytes("ISO-8859-1"), "UTF-8"));
            user.setHeadImg(userinfo.get("headimgurl"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        userMapper.addUser(user);
        return user;
    }

    private Map<String, String> getAccessTokenByCode(String code) {
        String getAccessTokenUrl = wxConfig.getGetAccessTokenUrl();
        getAccessTokenUrl = String.format(getAccessTokenUrl,
                wxConfig.getOpenAppid(),
                wxConfig.getOpenAppsecret(),
                code);

        Map<String, String> map = HttpUtils.doGet(getAccessTokenUrl, 5000);
        if (map != null) {
            System.out.println(map.get("access_token"));
        }
        return map;
    }

    private Map<String, String> getUserInfoByAccessToken(String accessToken, String openid) {
        String getUserinfoUrl = wxConfig.getGetUserinfoUrl();
        getUserinfoUrl = String.format(getUserinfoUrl, accessToken, openid);
        Map<String, String> map = HttpUtils.doGet(getUserinfoUrl, 5000);
        return map;
    }

}
