package com.server.hispath.auth.presentation;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

import com.server.hispath.auth.application.AuthService;
import com.server.hispath.auth.domain.ManagerLogin;
import com.server.hispath.auth.domain.StudentLogin;
import com.server.hispath.util.AuthorizationExtractor;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginManagerArgumentResolver implements HandlerMethodArgumentResolver {
    private final AuthService authService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ManagerLogin.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String accessToken = AuthorizationExtractor
                .extractAccessToken(Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class)));
        return authService.findManagerByToken(accessToken);
    }
}
