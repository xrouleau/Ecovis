package com.example.intranetecovis.security;

import com.example.intranetecovis.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;

/***
 * Classe qui s'occupe de la sécurité et de l'authentification
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // déclaration des variables de classe
    private final CustomUserDetailsService customUserDetailsService;

    /***
     * Constructeur de la classe SecurityConfig
     * @param customUserDetailsService Service pour la connexion d'un utilisateur
     */
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                            "/",
                            "/WEB-INF/**",
                            "/css/**",
                            "/images/**",
                            "/connexion",
                            "/error"
                    ).permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> {}).headers(headers -> {
                    headers.xssProtection(xss ->
                            xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
                    ).contentSecurityPolicy(csp ->
                            csp.policyDirectives("script-src 'self'; style-src 'self' https://cdn.jsdelivr.net; object-src 'none';"));
                })
                .csrf(Customizer.withDefaults())
                .formLogin(form -> form.loginPage("/connexion")
                        .defaultSuccessUrl("/nouvelles")
                        .loginProcessingUrl("/connexionSubmit")
                        .failureUrl("/error")
                        .permitAll())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/deconnexion"))
                        .permitAll())
        ;
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
