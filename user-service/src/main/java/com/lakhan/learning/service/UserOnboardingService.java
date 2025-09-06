package com.lakhan.learning.service;


import com.lakhan.learning.dtos.UserProfileRequest;
import com.lakhan.learning.dtos.UserProfileResponse;
import com.lakhan.learning.entities.User;
import com.lakhan.learning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserOnboardingService {

    private final UserRepository userRepository;

    /**
     * Creates a new User entity from a UserProfileRequest DTO.
     * Returns the saved UserProfileResponse DTO.
     */
    public UserProfileResponse createUser(UserProfileRequest request) {
        // Map DTO -> Entity
        com.lakhan.learning.entities.User user = com.lakhan.learning.entities.User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .name(request.getName())
                .profilePictureUrl(request.getProfilePictureUrl())
                .bio(request.getBio())
                .interests(request.getInterests())
                .followersCount(0)
                .followingCount(0)
                .postsCount(0)
                .commentsCount(0)
                .points(0)
                .badgesCount(0)
                .onboarded(true)  // after first wizard
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();

        // Save to DB
        User savedUser = userRepository.save(user);

        // Map Entity -> Response DTO
        return UserProfileResponse.fromUser(savedUser);
    }
}
