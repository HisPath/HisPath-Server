package com.server.hispath.auth.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.hispath.auth.application.AuthService;
import com.server.hispath.auth.domain.*;
import com.server.hispath.auth.infrastructure.JwtProvider;
import com.server.hispath.util.AuthorizationExtractor;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;
    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (isPreflight(request)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (!(hasAnnotation(handlerMethod))) {
            return true;
        }
        String token = AuthorizationExtractor.extractAccessToken(request);

        if (handlerMethod.hasMethodAnnotation(RequiredStudentLogin.class)) {
            jwtProvider.validateToken(token, Member.STUDENT);
        } else if (handlerMethod.hasMethodAnnotation(RequiredManagerLogin.class)) {
            jwtProvider.validateToken(token, Member.MANAGER);
        } else if (handlerMethod.hasMethodAnnotation(RequiredLogin.class)){
            jwtProvider.validateBothToken(token);
        } else if (handlerMethod.hasMethodAnnotation(RequiredSuperManagerLogin.class)){
            authService.checkSuperManagerByToken(token);
        }

        return true;
    }

    private boolean hasAnnotation(HandlerMethod handlerMethod){
       return handlerMethod.hasMethodAnnotation(RequiredLogin.class)
               || handlerMethod.hasMethodAnnotation(RequiredStudentLogin.class)
               || handlerMethod.hasMethodAnnotation(RequiredManagerLogin.class);
    }

    private boolean isPreflight(HttpServletRequest request) {
        return HttpMethod.OPTIONS.matches(request.getMethod());
    }
}