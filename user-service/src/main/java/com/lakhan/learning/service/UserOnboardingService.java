package com.lakhan.learning.service;


import com.lakhan.learning.config.JwtUtility;
import com.lakhan.learning.dao.InterestDao;
import com.lakhan.learning.dtos.UserOnboardingRequest;
import com.lakhan.learning.dtos.UserOnboardingResponse;
import com.lakhan.learning.entities.Interest;
import com.lakhan.learning.entities.User;
import com.lakhan.learning.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lakhan.learning.constant.Constants.DELIMITER;

@Service
@RequiredArgsConstructor
public class UserOnboardingService {

    private final UserRepository userRepository;
    private final InterestDao interestDao;
    private final JwtUtility jwtUtility;

    public UserOnboardingResponse signUp(String email, String name) {
        User user = User.builder()
                .email(email)
                .name(name)
                .followersCount(0)
                .followingCount(0)
                .postsCount(0)
                .commentsCount(0)
                .points(0)
                .badgesCount(0)
                .onboarded(true)
                .isActive(false)
                .createdAt(LocalDateTime.now())
                .build();
        User savedUser = userRepository.save(user);
        return UserOnboardingResponse.fromUser(savedUser);
    }

    @Transactional
    public UserOnboardingResponse onboardUser(UserOnboardingRequest request, String authHeader) {

        Long userId = jwtUtility.extractUserIdFromAuthHeader(authHeader);

        Set<Interest> interests = Arrays.stream(request.getInterests().split(DELIMITER))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .map(name -> interestDao.findByName(name)
                        .orElseGet(() -> {
                            Interest newInterest = new Interest();
                            newInterest.setName(name);
                            return interestDao.save(newInterest); // persist new
                        })
                )
                .collect(Collectors.toSet());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User must exists in db. But User not found with id: " + userId));
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setBio(request.getBio());
        user.setInterests(interests);
        user.setOnboarded(true);
        user.setActive(true);
        User savedUser = userRepository.save(user);
        return UserOnboardingResponse.fromUser(savedUser);
    }

    private Long getLoggedInUserId() {

        String tmp = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return Long.valueOf((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return 1L;
    }

    public Optional<UserOnboardingResponse> getProfileByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserOnboardingResponse::fromUser);
    }

    public boolean validateUsernameUniqueness(String username) {
        return !userRepository.existsByUsername(username);

    }
}