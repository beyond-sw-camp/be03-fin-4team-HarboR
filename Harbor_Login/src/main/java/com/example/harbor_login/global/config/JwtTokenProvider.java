package com.example.harbor_login.global.config;

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
    public String createToken(String email, String role, String employeeId){
        Claims claims = Jwts.claims().setSubject(employeeId);
        claims.put("role", role);
        claims.put("myEmail", email);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration*60*100000L))
                .signWith(SignatureAlgorithm.HS256, secretKey)
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
     *
     * @param token jwt token
     * @return Claims Information
     */
    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
    public String getEmployeeId(String token) {
        Claims claims = getClaims(token);
        return (String) claims.get("employeeId");
    }
}

