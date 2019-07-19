package com.diaolizhi.wxloginpay.controller;

import com.diaolizhi.wxloginpay.config.WXConfig;
import com.diaolizhi.wxloginpay.domain.User;
import com.diaolizhi.wxloginpay.domain.Video;
import com.diaolizhi.wxloginpay.domain.VideoOrder;
import com.diaolizhi.wxloginpay.mapper.UserMapper;
import com.diaolizhi.wxloginpay.mapper.VideoMapper;
import com.diaolizhi.wxloginpay.service.VideoOrderService;
import com.diaolizhi.wxloginpay.utils.IpUtils;
import com.diaolizhi.wxloginpay.utils.WXPayUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: wx-login-pay
 * @description: 视频下单接口
 * @author: diaolizhi
 * @create: 2019-07-18 23:00
 **/

@RestController
@RequestMapping("/api/v1/video_order")
public class VideoOrderController {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoOrderService videoOrderService;

    @Autowired
    private WXConfig wxConfig;

    @GetMapping("/add")
    public void add(@RequestParam(name = "user_id") int userId,
                      @RequestParam(name = "video_id") int videoId,
                      HttpServletRequest request,
                      HttpServletResponse response) throws Exception {

        String ip = IpUtils.getIpAddr(request);

        System.out.println("ip： " + ip);

        String codeUrl = videoOrderService.saveVideoOrder(userId, videoId, ip);

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(codeUrl, BarcodeFormat.QR_CODE, 300, 300);

        ServletOutputStream os = response.getOutputStream();

        MatrixToImageWriter.writeToStream(bitMatrix, "png", os);
    }

    @RequestMapping("callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws Exception {

        boolean res = videoOrderService.updateVideoOrder(request);

        if (res) {
            response.setContentType("text/xml");
            response.getWriter().println("success");
        } else {
            response.setContentType("text/xml");
            response.getWriter().println("fail");
        }
    }

}
