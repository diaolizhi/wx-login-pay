package com.diaolizhi.wxloginpay.controller;

import com.diaolizhi.wxloginpay.domain.JsonData;
import com.diaolizhi.wxloginpay.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: wx-login-pay
 * @description:
 * @author: diaolizhi
 * @create: 2019-07-18 22:51
 **/

@RestController
@RequestMapping
public class IndexController {

    @GetMapping("/")
    public JsonData index(@RequestParam(name = "id") int id,
                          @RequestParam(name = "username") String username,
                          @RequestParam(name = "head_img") String headImg) {
        User user = new User();
        user.setId(id);
        user.setHeadImg(headImg);
        user.setUsername(username);
        return JsonData.buildSuccess(user);
    }

}
