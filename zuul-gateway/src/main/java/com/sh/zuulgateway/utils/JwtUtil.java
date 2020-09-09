package com.sh.zuulgateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /*签名秘钥*/
    private static final String SIGNATURE_KEY="L34iu*06jklKNEzhqm8uydscnode-96?k";

    public String createJWT(Date exp, Map<String,Object> claims  ){
        /*指定签名算法*/
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;

        //JWT生成时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //创建一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //保存在Payload（有效载荷）中的内容
                .setClaims(claims)
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //设置过期时间
                .setExpiration(exp)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, SIGNATURE_KEY);

        return builder.compact();
    }

    /**
     * 解析token，获取到Payload（有效载荷）中的内容，包括验证签名，判断是否过期
     *
     * @param token
     * @return
     */
    public Claims parseJWT(String token) {
        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(SIGNATURE_KEY)
                //设置需要解析的token
                .parseClaimsJws(token).getBody();
        return claims;
    }
}
