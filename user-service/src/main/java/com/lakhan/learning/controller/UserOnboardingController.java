package com.lakhan.learning.controller;

import com.lakhan.learning.dtos.UserOnboardingRequest;
import com.lakhan.learning.dtos.UserOnboardingResponse;
import com.lakhan.learning.repository.UserRepository;
import com.lakhan.learning.service.UserOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/secured/onboarding")
public class UserOnboardingController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserOnboardingService userOnboardingService;

    @GetMapping("/auth/get-profile")
    public ResponseEntity<UserOnboardingResponse> getProfile(@AuthenticationPrincipal OAuth2User principal) {
        Optional<UserOnboardingResponse> profile = userOnboardingService.getProfileByEmail(principal.getAttribute("email"));
        return profile
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/auth/create")
    public ResponseEntity<UserOnboardingResponse> onboardUser(@RequestBody UserOnboardingRequest request, @RequestHeader("Authorization") String authHeader) {
        UserOnboardingResponse response = userOnboardingService.onboardUser(request, authHeader);
        return ResponseEntity.ok(response);
    }

}
