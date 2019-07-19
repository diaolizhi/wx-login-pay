package com.diaolizhi.wxloginpay.mapper;

import com.diaolizhi.wxloginpay.domain.Video;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VideoMapper {

    @Select("SELECT * FROM video WHERE id = #{id}")
    Video findVideoById(int id);

    @Select("SELECT * FROM video")
    List<Video> findAllVideo();
}
