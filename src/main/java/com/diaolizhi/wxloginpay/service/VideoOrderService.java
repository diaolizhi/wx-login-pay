package com.diaolizhi.wxloginpay.service;

import javax.servlet.http.HttpServletRequest;

public interface VideoOrderService {

    String saveVideoOrder(int userId, int videoId, String ip) throws Exception;

    boolean updateVideoOrder(HttpServletRequest request) throws Exception;

}
