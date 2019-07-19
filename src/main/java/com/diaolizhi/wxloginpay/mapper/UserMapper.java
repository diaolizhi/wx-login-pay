package com.diaolizhi.wxloginpay.mapper;

import com.diaolizhi.wxloginpay.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Insert("INSERT INTO user (username, openid, head_img) VALUES (#{username}, #{openid}, #{headImg})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int addUser(User user);

    @Select("SELECT * FROM USER WHERE id = #{id}")
    User findUserById(int id);

    @Select("SELECT * FROM USER WHERE openid = #{openid}")
    User findUserByOpenid(String openid);
}
