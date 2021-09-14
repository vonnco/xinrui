package com.xinrui.framework.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class JwtUtil {
    //签名私钥
    private String key;
    //签名失效时间
    private Long ttl;

    /**
     * 创建jwt
     * @param id 用户id
     * @param username 用户名
     * @param map 用户参数
     * @return
     */
    public String createJwt(String id, String username, Map<String,Object> map){
        //当前毫秒
        long now = System.currentTimeMillis();
        //失效时间
        long exp = now + ttl;
        JwtBuilder jwtBuilder = null;
        try {
            jwtBuilder = Jwts.builder()
                    //设置jwt的ID
                    .setId(id)
                    //设置jwt主题
                    .setSubject(username)
                    //设置签发时间
                    .setIssuedAt(new Date())
                    //设置签名
                    .signWith(SignatureAlgorithm.HS256, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置claims
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            jwtBuilder.claim(entry.getKey(),entry.getValue());
        }
        //设置过期时间
        jwtBuilder.setExpiration(new Date(exp));
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解析token获取claims
     * @param token
     * @return
     */
    public Claims parseJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }
}
