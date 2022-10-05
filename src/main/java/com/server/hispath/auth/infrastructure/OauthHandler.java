package com.server.hispath.auth.infrastructure;

import java.util.Map;

import com.server.hispath.auth.domain.OauthAttributes;
import com.server.hispath.auth.domain.OauthProvider;
import com.server.hispath.auth.domain.OauthUserInfo;
import com.server.hispath.exception.oauth.UnsupportedOauthProviderException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OauthHandler {
    private final Map<String, OauthProvider> oauthProviders;
    private final ApiRequester apiRequester;

    public OauthUserInfo getUserInfoFromCode(String oauthProvider, String code) {
        OauthProvider oauth = getOauthProvider(oauthProvider);
        Map<String, Object> attributes = apiRequester.getUserInfo(code, oauth);
        return OauthAttributes.extract(oauthProvider, attributes);
    }

    private OauthProvider getOauthProvider(String oauthProvider) {
        if (!oauthProviders.containsKey(oauthProvider)) {
            throw new UnsupportedOauthProviderException();
        }
        return oauthProviders.get(oauthProvider);
    }
}
