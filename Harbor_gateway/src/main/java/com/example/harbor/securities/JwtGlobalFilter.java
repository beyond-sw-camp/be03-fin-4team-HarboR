package com.example.harbor.securities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class JwtGlobalFilter implements GlobalFilter {
    @Value("${jwt.secretKey}")
    private String secretKey;

    private final List<String> allowUrl = Arrays.asList("/login/account/**", "/signup","/login/notice/download/**");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String reqUri = request.getURI().getPath();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        log.info(reqUri);
        boolean isAllowed = allowUrl.stream().anyMatch(uri -> antPathMatcher.match(uri, reqUri));
        if (isAllowed) {
            return chain.filter(exchange);
        }
        try {
            String bearertoken = request.getHeaders().getFirst("Authorization");

            if (bearertoken != null) {
                if (!bearertoken.startsWith("Bearer ")) {
                    throw new IllegalArgumentException("token 의 형식이 맞지 않습니다.");
                }
//            토큰값 추출
                String token = bearertoken.substring(7);
//            추출된토큰 검증 후 Authentication객체 생성
//            검증도 및 claims 추출
                Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
                String employeeId = claims.getSubject();
                String email = claims.get("myEmail", String.class);
                String role = claims.get("role", String.class);
                request = exchange.getRequest().mutate()
                        .header("employeeId", employeeId)
                        .header("myEmail", email)
                        .header("role", role)
                        .build();
                exchange = exchange.mutate().request(request).build();
            }
            else {
                throw new RuntimeException("token is empty");
            }
        } catch (Exception e) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            e.printStackTrace();
            System.out.println(e.getMessage());
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
}
