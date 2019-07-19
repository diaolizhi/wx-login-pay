package com.diaolizhi.wxloginpay.service.impl;

import com.diaolizhi.wxloginpay.config.WXConfig;
import com.diaolizhi.wxloginpay.domain.User;
import com.diaolizhi.wxloginpay.domain.Video;
import com.diaolizhi.wxloginpay.domain.VideoOrder;
import com.diaolizhi.wxloginpay.mapper.UserMapper;
import com.diaolizhi.wxloginpay.mapper.VideoMapper;
import com.diaolizhi.wxloginpay.mapper.VideoOrderMapper;
import com.diaolizhi.wxloginpay.service.VideoOrderService;
import com.diaolizhi.wxloginpay.utils.CommonUtils;
import com.diaolizhi.wxloginpay.utils.HttpUtils;
import com.diaolizhi.wxloginpay.utils.WXPayUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.*;

/**
 * @program: wx-login-pay
 * @description:
 * @author: diaolizhi
 * @create: 2019-07-18 23:14
 **/

@Service
public class VideoOrderServiceImpl implements VideoOrderService {


    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private WXConfig wxConfig;

    @Override
    public String saveVideoOrder(int userId, int videoId, String ip) throws Exception {
        Video video = videoMapper.findVideoById(videoId);
        User user = userMapper.findUserById(userId);

        VideoOrder videoOrder = new VideoOrder();

        videoOrder.setOutTradeNo(CommonUtils.generateUUID());
        videoOrder.setCreateTime(new Date(System.currentTimeMillis()));

        videoOrder.setUserId(user.getId());
        videoOrder.setUserName(user.getUsername());
        videoOrder.setVideoId(video.getId());
        videoOrder.setVideoCover(video.getVideoCover());
        videoOrder.setVideoTitle(video.getVideoTitle());
        videoOrder.setVideoPrice(video.getPrice());
        videoOrder.setIp(ip);

        videoOrderMapper.addVideoOrder(videoOrder);

//        必须是有序的 Map
        SortedMap<String, String> map = new TreeMap<>();
        map.put("appid", wxConfig.getAppid());
        map.put("mch_id", wxConfig.getMerId());
        map.put("nonce_str", CommonUtils.generateUUID());
        map.put("body", videoOrder.getVideoTitle());
        map.put("out_trade_no", videoOrder.getOutTradeNo());
        map.put("total_fee", String.valueOf(videoOrder.getVideoPrice()));
        map.put("spbill_create_ip", videoOrder.getIp());
        map.put("notify_url", wxConfig.getPayCallBackUrl());
        map.put("trade_type", "APP");

        String sign = WXPayUtil.createSign(map, wxConfig.getKey());
        map.put("sign", sign);
        String xml = WXPayUtil.mapToXml(map);

        String codeUrl = getCodeUrl(xml);

        return codeUrl;
    }

    @Override
    public boolean updateVideoOrder(HttpServletRequest request) throws Exception {

        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String xml = sb.toString();

        SortedMap<String, String> map = WXPayUtil.getSortedMap(WXPayUtil.xmlToMap(xml));

        if (WXPayUtil.isCorrectSign(map, wxConfig.getKey())) {
            if("SUCCESS".equals(map.get("result_code"))) {

                String outTradeNo = map.get("out_trade_no");

                VideoOrder videoOrder = videoOrderMapper.findVideoOrderByOutTradeNo(outTradeNo);

                if (videoOrder != null && videoOrder.getState() == 0) {
                    videoOrder.setOpenid(map.get("openid"));
                    videoOrder.setNotifyTime(new Date());
                    videoOrder.setOutTradeNo(outTradeNo);
                    videoOrder.setState(1);
                    int res = videoOrderMapper.updateVideoOrder(videoOrder);
                    if (res == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 调用微信统一下单接口，获取 code_url
     * @param xml
     * @return
     * @throws Exception
     */
    private String getCodeUrl(String xml) throws Exception {
        String unifiedorderUrl = wxConfig.getUnifiedorderUrl();
        String res = HttpUtils.doPost(unifiedorderUrl, xml, 5000);
        Map<String, String> map = WXPayUtil.xmlToMap(res);
        return map.get("code_url");
    }

}
