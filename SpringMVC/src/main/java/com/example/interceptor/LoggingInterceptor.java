package com.example.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {
    private static final String JSESSIONID = "JSESSIONID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String sessionId = null;

        if (request.getCookies() != null){
            for (Cookie cookie: request.getCookies()) {
                if (cookie.getName().equals(JSESSIONID)){
                    sessionId = cookie.getValue();
                }
            }
        }
        log.info("Incoming request data: session: {} at {} for request URI: {}", sessionId, new Date(), request.getRequestURI());
        return true;
    }
}
