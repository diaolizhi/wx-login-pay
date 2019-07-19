package com.diaolizhi.wxloginpay.mapper;

import com.diaolizhi.wxloginpay.domain.VideoOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface VideoOrderMapper {

    @Insert("INSERT INTO video_order (out_trade_no, user_id, user_name, ip, video_id, video_title, video_cover, video_price, create_time) " +
            "VALUES " +
            "(#{outTradeNo}, #{userId}, #{userName}, #{ip}, #{videoId}, #{videoTitle}, #{videoCover}, #{videoPrice}, #{createTime})")
    int addVideoOrder(VideoOrder videoOrder);

    @Select("SELECT * FROM video_order WHERE out_trade_no = #{outTradeNo}")
    VideoOrder findVideoOrderByOutTradeNo(String outTradeNo);

    @Update("UPDATE video_order SET state=#{state}, openid=#{openid}, notify_time=#{notifyTime} WHERE out_trade_no = #{outTradeNo}")
    int updateVideoOrder(VideoOrder videoOrder);
}
