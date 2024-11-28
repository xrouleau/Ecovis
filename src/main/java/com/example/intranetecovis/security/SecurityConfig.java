package com.example.intranetecovis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                            "/",
                            "/login",
                            "/register",
                            "/WEB-INF/**",
                            "/liste",
                            "/css/**",
                            "/images/**"
                    ).permitAll();
                    auth.anyRequest().authenticated();
                }).formLogin(form -> {}).headers(headers -> {
                    headers.xssProtection(xss ->
                            xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
                    ).contentSecurityPolicy(csp ->
                            csp.policyDirectives("script-src 'self'; style-src 'self' https://cdn.jsdelivr.net; object-src 'none';"));
                }).csrf(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
