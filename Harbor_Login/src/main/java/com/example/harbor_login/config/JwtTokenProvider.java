package com.example.harbor_login.config;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    //사용자 이메일과 롤
    public String createToken(String email, String role){
        //claims : 클레임은 토큰 사용자에 대한 속성이나 데이터포함, 주로 페이로드를 의미.
        //기본
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration*60*1000L))//30분
                .signWith(SignatureAlgorithm.HS256, "mysecret")
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            final Date expiration = getClaims(token).getExpiration();
            return !expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param token jwt token
     * @return Claims Information
     */
    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}

