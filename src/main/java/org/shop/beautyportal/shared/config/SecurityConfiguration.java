package org.shop.beautyportal.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    @Order(2)
    SecurityFilterChain spa(HttpSecurity http, ClientRegistrationRepository repo) throws Exception {
        var logoutHandler = new OidcClientInitiatedLogoutSuccessHandler(repo);
        logoutHandler.setPostLogoutRedirectUri("/");

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.ignoringRequestMatchers(
                        // docs/dev tools
                        "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
                        // H2 console posts without a CSRF token
                        "/h2-console/**"
                ))
                .headers(h -> h
                        // H2 console uses frames
                        .frameOptions(f -> f.sameOrigin())
                )
                .authorizeHttpRequests(a -> a
                        // static assets
                        .requestMatchers("/", "/index.html", "/favicon.ico",
                                "/assets/**", "/static/**", "/manifest.json", "/logo-**.png")
                        .permitAll()
                        // allow the console itself
                        .requestMatchers("/h2-console/**").permitAll()
                        // everything else goes through OIDC
                        .anyRequest().authenticated()
                )
                .oauth2Login(o -> o.defaultSuccessUrl("/", true))
                .logout(l -> l.logoutSuccessHandler(logoutHandler));

        return http.build();
    }

}
