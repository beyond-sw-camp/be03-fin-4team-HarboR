//package com.example.harbor_login.global.config;
//
////CORS 설정 vue 사용시 포트 달라지기 때문
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**")
//                .al lowedOrigins("http://localhost:3000") //vue의 url
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .allowCredentials(true); //보안처리 관련 credentials
//    }
//}