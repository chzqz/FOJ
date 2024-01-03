package icu.chzqz.foj.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Date;
import java.util.Map;

/**
 * JWT密钥工具类
 */
@Component
@Data
public class JWTUtil {


    private static String key;

    private static Long effectiveTime;

    @Value("${foj.jwt.key}")
    private void setKey(String key){
        JWTUtil.key = key;
    }
    @Value("${foj.jwt.effective-time}")
    private void setKey(Long effectiveTime){
        JWTUtil.effectiveTime = effectiveTime;
    }
    public static String createToken(Map claims){
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + effectiveTime))
                .compact();
        return token;
    }

    public static Map parseToken(String token){
        Map claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)))
                .build()
                .parseClaimsJws(token).getBody();
        return claims;
    }

}
