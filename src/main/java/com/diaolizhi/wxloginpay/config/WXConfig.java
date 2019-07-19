package com.diaolizhi.wxloginpay.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: wx-login-pay
 * @description: 微信配置类
 * @author: diaolizhi
 * @create: 2019-07-18 18:05
 **/

@Configuration
@Getter
public class WXConfig {

    @Value("${wxopen.open_appid}")
    private String openAppid;

    @Value("${wxopen.open_appsecret}")
    private String openAppsecret;

    @Value("${wxopen.redirect_url}")
    private String redirectUrl;

    @Value("${wxopen.login_url}")
    private String loginUrl;

    @Value("${wxopen.get_access_token_url}")
    private String getAccessTokenUrl;

    @Value("${wxopen.get_userinfo_url}")
    private String getUserinfoUrl;

    @Value("${wxpay.unifiedorder_url}")
    private String unifiedorderUrl;

    @Value("${wxpay.appid}")
    private String appid;

    @Value("${wxpay.appsecret}")
    private String appsecret;

    @Value("${wxpay.mer_id}")
    private String merId;

    @Value("${wxpay.key}")
    private String key;

    @Value("${wxpay.pay_callback_url}")
    private String payCallBackUrl;
}
