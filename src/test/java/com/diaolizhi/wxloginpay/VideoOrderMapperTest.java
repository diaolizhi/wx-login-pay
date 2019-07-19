package com.diaolizhi.wxloginpay;

import com.diaolizhi.wxloginpay.domain.VideoOrder;
import com.diaolizhi.wxloginpay.mapper.VideoOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.Date;

/**
 * @program: wx-login-pay
 * @description:
 * @author: diaolizhi
 * @create: 2019-07-18 23:18
 **/

@RunWith(SpringRunner.class) // 底层用 JUnit
@SpringBootTest(classes = {WxLoginPayApplication.class}) // 启动整个 SpringBoot 项目
public class VideoOrderMapperTest {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Test
    public void test() {
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setOutTradeNo("aaaaaaaaaaaaaaaaaaaaa");
        videoOrder.setUserId(1);
        videoOrder.setUserName("张三");
        videoOrder.setVideoId(5);
        videoOrder.setVideoPrice(1000);
        videoOrder.setVideoTitle("Git 教程");
        videoOrder.setVideoCover("http://jpg.com/a.png");
        videoOrder.setCreateTime(new Date(System.currentTimeMillis()));
        videoOrderMapper.addVideoOrder(videoOrder);
    }


    @Test
    public void test1() {
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setOpenid("77777777777");
        videoOrder.setOpenid(videoOrder.getOpenid());
        videoOrder.setNotifyTime(new Date());
        videoOrder.setOutTradeNo("b8a979c56d8a4096bf8d9aba80c47449");
        videoOrder.setState(1);
        videoOrderMapper.updateVideoOrder(videoOrder);
    }
}
