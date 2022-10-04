package com.server.hispath.config;

import java.util.List;

import com.server.hispath.auth.presentation.LoginInterceptor;
import com.server.hispath.auth.presentation.LoginManagerArgumentResolver;
import com.server.hispath.auth.presentation.LoginStudentArgumentResolver;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;
    private final LoginStudentArgumentResolver loginStudentArgumentResolver;
    private final LoginManagerArgumentResolver loginManagerArgumentResolver;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:3000", "http://localhost:8080");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/api/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginStudentArgumentResolver);
        resolvers.add(loginManagerArgumentResolver);
    }
}