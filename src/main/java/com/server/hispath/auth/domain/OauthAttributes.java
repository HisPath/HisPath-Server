package com.server.hispath.auth.domain;

import java.util.Arrays;
import java.util.Map;

public enum OauthAttributes {
    GOOGLE("google") {
        @Override
        public OauthUserInfo of(Map<String, Object> attributes) {
            return OauthUserInfo.builder()
                                .email((String) attributes.get("email"))
                                .build();
        }
    };

    private final String providerName;

    OauthAttributes(String name) {
        this.providerName = name;
    }

    public static OauthUserInfo extract(String providerName, Map<String, Object> attributes) {
        return Arrays.stream(values())
                     .filter(provider -> providerName.equals(provider.providerName))
                     .findFirst()
                     .orElseThrow(IllegalArgumentException::new)
                     .of(attributes);
    }

    public abstract OauthUserInfo of(Map<String, Object> attributes);
}
