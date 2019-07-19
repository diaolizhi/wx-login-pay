package com.diaolizhi.wxloginpay.utils;

import com.diaolizhi.wxloginpay.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @program: wx-login-pay
 * @description: JWT 工具类
 * @author: diaolizhi
 * @create: 2019-07-18 21:50
 **/
public class JWTUtils {

    private static final String SUBJECT = "dbddjgja";

    private static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;

    public static final String APPSECRET = "ylybd";

    public static String geneToken(User user) {
        if (user == null || user.getId() == null || user.getUsername() == null
                || user.getHeadImg() == null) {
            return null;
        }

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("headImg", user.getHeadImg())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET)
                .compact();

        return token;
    }

    public static Claims checkJWT(String token) {
        try {
            //        解析到错误的 token 会抛出异常
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
