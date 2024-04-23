package com.example.harbor_total.global.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Object result = modelAndView.getModel().get("result");

        if (result != null) {
            request.getSession().setAttribute("result", result);
            response.sendRedirect("/home");
        }
    }
}
