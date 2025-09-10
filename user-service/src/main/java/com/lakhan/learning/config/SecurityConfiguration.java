package com.lakhan.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthenticationFilter jwtAuthenticationFilter,  CustomOAuth2SuccessHandler customOAuth2SuccessHandler) throws Exception {
        return httpSecurity
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(req->
                        req.requestMatchers("/secured/**").authenticated()
                            .anyRequest().permitAll()

                        )
                .oauth2Login(oauth -> oauth.successHandler(customOAuth2SuccessHandler))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
