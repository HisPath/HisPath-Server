package com.server.hispath.config;

import java.util.Map;

import com.server.hispath.auth.domain.OauthProperties;
import com.server.hispath.auth.domain.OauthProvider;
import com.server.hispath.auth.infrastructure.ApiRequester;
import com.server.hispath.auth.infrastructure.OauthHandler;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(OauthProperties.class)
public class OauthConfig {

    private final OauthProperties properties;

    @Bean
    public OauthHandler oauthHandler() {
        Map<String, OauthProvider> providers = properties.getOauthProviders();
        return new OauthHandler(providers, new ApiRequester());
    }
}
