package com.diaolizhi.wxloginpay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.diaolizhi.wxloginpay.mapper")
public class WxLoginPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxLoginPayApplication.class, args);
    }

}
