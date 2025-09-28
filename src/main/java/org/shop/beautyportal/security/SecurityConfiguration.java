package org.shop.beautyportal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Class to configure AWS Cognito as an OAuth 2.0 authorizer with Spring Security.
 * In this configuration, we specify our OAuth Client.
 * We also declare that all requests must come from an authenticated user.
 * Finally, we configure our logout handler.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CognitoLogoutHandler cognitoLogoutHandler = new CognitoLogoutHandler();

        http.csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/index.html", "/static/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/h2-console", "/swagger-ui/index.html").permitAll()
                        .requestMatchers("/api/v1/sales-channels/**").permitAll()
                        //.requestMatchers("/api/v1/sales-channels/**").hasRole("DISTRIBUTOR")
                        .requestMatchers("/api/v1/purchase-report/**").hasRole("EXPORT_MANAGER")
                        .requestMatchers("/api/v1/media/**").hasAnyRole("DISTRIBUTOR", "EXPORT_MANAGER", "ADMIN", "SUPER_ADMIN")
                        .anyRequest().hasAnyRole("ADMIN", "SUPER_ADMIN")
                )
                .oauth2Login(Customizer.withDefaults())
                .logout(logout -> logout.logoutSuccessHandler(cognitoLogoutHandler));

        return http.build();
    }

     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // wyłączenie CSRF
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll() // zezwolenie na wszystkie żądania
                )
                .oauth2Login(login -> login.disable()) // wyłączenie logowania OAuth2
                .logout(logout -> logout.disable()); // wyłączenie logoutu

        return http.build();
    }
}
