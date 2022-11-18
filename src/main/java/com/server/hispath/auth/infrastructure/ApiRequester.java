package com.server.hispath.auth.infrastructure;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import com.server.hispath.auth.domain.Member;
import com.server.hispath.auth.domain.OauthProvider;
import com.server.hispath.exception.oauth.GetAccessTokenException;
import com.server.hispath.exception.oauth.GetUserInfoException;
import com.server.hispath.exception.oauth.UnableToGetOauthResponseException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class ApiRequester {

    public Map<String, Object> getUserInfo(String code, OauthProvider oauthProvider, Member member) {
        String token = getToken(code, oauthProvider, member);
        return getUserInfoByToken(token, oauthProvider.getUserInfoUrl());
    }

    private String getToken(String code, OauthProvider oauthProvider, Member member) {
        Map<String, Object> responseBody = WebClient.create()
                                                    .post()
                                                    .uri(oauthProvider.getTokenUrl())
                                                    .headers(header -> {
                                                        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                                                        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                                                        header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                                                    })
                                                    .bodyValue(tokenRequest(code, oauthProvider, member))
                                                    .retrieve()
                                                    .onStatus(HttpStatus::isError, response ->
                                                            response.bodyToMono(String.class)
                                                                    .flatMap(error -> Mono.error(new UnableToGetOauthResponseException(error))))
                                                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                                                    })
                                                    .flux()
                                                    .toStream()
                                                    .findFirst()
                                                    .orElseThrow(GetAccessTokenException::new);
        validateResponseBody(responseBody);
        return responseBody.get("access_token").toString();
    }

    private String getRedirectUrlByMember(Member member, OauthProvider oauthProvider) {

        if (Member.isStudent(member)) {
            return oauthProvider.getStudentRedirectUrl();
        }
        return oauthProvider.getManagerRedirectUrl();

    }

    private MultiValueMap<String, String> tokenRequest(String code, OauthProvider oauthProvider, Member member) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", getRedirectUrlByMember(member, oauthProvider));
        formData.add("client_id", oauthProvider.getClientId());
        formData.add("client_secret", oauthProvider.getClientSecret());
        return formData;
    }

    private void validateResponseBody(Map<String, Object> responseBody) {
        if (!responseBody.containsKey("access_token")) {
            throw new GetAccessTokenException();
        }
    }

    private static Map<String, Object> getUserInfoByToken(String token, String userInfoUri) {

        return WebClient.create()
                        .get()
                        .uri(userInfoUri)
                        .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                        .retrieve()
                        .onStatus(HttpStatus::isError, response ->
                                response.bodyToMono(String.class)
                                        .flatMap(error -> Mono.error(new UnableToGetOauthResponseException(error))))
                        .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                        })
                        .flux()
                        .toStream()
                        .findFirst()
                        .orElseThrow(GetUserInfoException::new);
    }
}
