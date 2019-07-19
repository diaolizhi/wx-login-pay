package com.diaolizhi.wxloginpay.controller;

import com.diaolizhi.wxloginpay.config.WXConfig;
import com.diaolizhi.wxloginpay.domain.JsonData;
import com.diaolizhi.wxloginpay.domain.User;
import com.diaolizhi.wxloginpay.service.UserService;
import com.diaolizhi.wxloginpay.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @program: wx-login-pay
 * @description: 微信登录接口
 * @author: diaolizhi
 * @create: 2019-07-18 18:07
 **/

@RestController
@RequestMapping("/pub/api/v1/")
public class LoginController {

    @Autowired
    private WXConfig wxConfig;

    @Autowired
    private UserService userService;

    @GetMapping("get_login_url")
    public JsonData getLoginUrl(@RequestParam(name = "state") String state) {

        String loginUrl = wxConfig.getLoginUrl();
        String appid = wxConfig.getOpenAppid();
        String redirectUrl = wxConfig.getRedirectUrl();
        loginUrl = String.format(loginUrl, appid, redirectUrl, state);

        return JsonData.buildSuccess(loginUrl);
    }

    @GetMapping("login_callback")
    public void loginCallback(@RequestParam(name = "code") String code,
                                  @RequestParam(name = "state") String state,
                                  HttpServletResponse response) throws IOException {

        User user = userService.saveUser(code);

        String token = JWTUtils.geneToken(user);

        response.sendRedirect(state + "?token=" + token
                + "&id=" + user.getId()
                + "&username=" + URLEncoder.encode(user.getUsername(), "UTF-8")
                + "&head_img=" + user.getHeadImg());
    }

}
