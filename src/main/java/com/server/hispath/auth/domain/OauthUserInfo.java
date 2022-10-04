package com.server.hispath.auth.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthUserInfo {
    private String email;
}
