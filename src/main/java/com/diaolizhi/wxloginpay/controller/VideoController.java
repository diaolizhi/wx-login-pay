package com.diaolizhi.wxloginpay.controller;

import com.diaolizhi.wxloginpay.domain.JsonData;
import com.diaolizhi.wxloginpay.domain.Video;
import com.diaolizhi.wxloginpay.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: wx-login-pay
 * @description: 视频接口
 * @author: diaolizhi
 * @create: 2019-07-19 10:26
 **/

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    @Autowired
    private VideoMapper videoMapper;

    @RequestMapping("list")
    public JsonData list() {
        List<Video> videos = videoMapper.findAllVideo();
        return JsonData.buildSuccess(videos);
    }

}
