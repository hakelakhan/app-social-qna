package com.lakhan.learning.services;

import com.lakhan.learning.entities.User;
import com.lakhan.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserFollowService {

    private final UserRepository userRepository;

    @Autowired
    public UserFollowService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void followUser(Long userId, Long followerId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User follower = userRepository.findById(followerId).orElseThrow(() -> new RuntimeException("Follower not found"));
        user.getFollowers().add(follower);
        follower.getFollowing().add(user);
        userRepository.save(user);
        userRepository.save(follower);
    }

    public void unfollowUser(Long userId, Long followerId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User follower = userRepository.findById(followerId).orElseThrow(() -> new RuntimeException("Follower not found"));
        user.getFollowers().remove(follower);
        follower.getFollowing().remove(user);
        userRepository.save(user);
        userRepository.save(follower);
    }

    public Set<UserFollowDto> listFollowers(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowers().stream()
                .map(UserFollowDto::fromUser)
                .collect(Collectors.toSet());
    }

    public Set<UserFollowDto> listFollowing(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowing().stream()
                .map(UserFollowDto::fromUser)
                .collect(Collectors.toSet());
    }
}

