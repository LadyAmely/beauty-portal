package org.shop.beautyportal.shared.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

public class CognitoLogoutHandler extends SimpleUrlLogoutSuccessHandler {

    private String domain = "https://eu-north-1hbbj2tuwg.auth.eu-north-1.amazoncognito.com";

    @Value("${spring.security.oauth2.client.registration.cognito.redirect-uri}")
    private String logoutRedirectUrl;

    @Value("${spring.security.oauth2.client.registration.cognito.client-id}")
    private String userPoolClientId;

    @Override
    protected String determineTargetUrl(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        return UriComponentsBuilder
                .fromUri(URI.create(domain + "/logout"))
                .queryParam("client_id", userPoolClientId)
                .queryParam("logout_uri", logoutRedirectUrl)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUriString();
    }
}
