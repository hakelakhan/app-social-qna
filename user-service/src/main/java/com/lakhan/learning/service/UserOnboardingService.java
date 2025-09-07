package com.lakhan.learning.service;


import com.lakhan.learning.dtos.*;
import com.lakhan.learning.entities.User;
import com.lakhan.learning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.lakhan.learning.constant.Constants.DELIMITER;

@Service
@RequiredArgsConstructor
public class UserOnboardingService {

    private final UserRepository userRepository;

    public UserOnboardingResponse onboardUser(UserOnboardingRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .name(request.getName())
                .bio(request.getBio())
                .interests(Interest.valueOf(request.getInterests(), DELIMITER))
                .followersCount(0)
                .followingCount(0)
                .postsCount(0)
                .commentsCount(0)
                .points(0)
                .badgesCount(0)
                .onboarded(true)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        return UserOnboardingResponse.fromUser(savedUser);
    }
}
