package com.lakhan.learning.controller;

import com.lakhan.learning.dtos.UserProfileResponse;
import com.lakhan.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/secured/onboarding")
public class UserOnboardingController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/auth/get-profile")
    public ResponseEntity<UserProfileResponse> getProfile(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");

        return userRepository.findByEmail(email)
                .map(user -> ResponseEntity.ok(UserProfileResponse.fromUser(user)))
                .orElseGet(() -> {
                    // New user → frontend will show onboarding wizard
                    return ResponseEntity.ok(
                            UserProfileResponse.builder()
                                    .isNewUser(true)
                                    .email(email)
                                    .active(true)       // default active
                                    .onboarded(false)   // not onboarded yet
                                    .followersCount(0)
                                    .followingCount(0)
                                    .postsCount(0)
                                    .commentsCount(0)
                                    .points(0)
                                    .badgesCount(0)
                                    .interests(null)
                                    .name(null)
                                    .username(null)
                                    .profilePictureUrl(null)
                                    .bio(null)
                                    .createdAt(null)
                                    .build()
                    );
                });
    }

}
