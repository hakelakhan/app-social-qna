package com.lakhan.learning.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakhan.learning.dtos.UserOnboardingResponse;
import com.lakhan.learning.service.UserOnboardingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtility jwtUtil;
    private final UserOnboardingService userOnboardingService;

    public CustomOAuth2SuccessHandler(JwtUtility jwtUtil, UserOnboardingService userOnboardingService) {
        this.jwtUtil = jwtUtil;
        this.userOnboardingService = userOnboardingService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");

        com.lakhan.learning.dtos.UserOnboardingResponse userOnboardingResponse;

        // Check if user exists
        Optional<UserOnboardingResponse> userProfileResponse = userOnboardingService.getProfileByEmail(email);
        // User does not exist, sign up
        userOnboardingResponse = userProfileResponse.orElseGet(() -> userOnboardingService.signUp(email, name));

        // Generate app JWT
        String jwt = jwtUtil.generateToken(userOnboardingResponse.getId() + "");
        userOnboardingResponse.setJwt(jwt);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        new ObjectMapper().writeValue(response.getWriter(), userOnboardingResponse);
    }
}
