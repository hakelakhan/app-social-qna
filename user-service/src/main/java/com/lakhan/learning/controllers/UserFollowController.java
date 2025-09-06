package com.lakhan.learning.controllers;

import com.lakhan.learning.services.UserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserFollowController {

    private final UserFollowService userFollowService;

    @Autowired
    public UserFollowController(UserFollowService userFollowService) {
        this.userFollowService = userFollowService;
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<?> followUser(@PathVariable Long userId, @RequestParam Long followerId) {
        userFollowService.followUser(userId, followerId);
        return ResponseEntity.ok("User followed successfully");
    }

    @PostMapping("/{userId}/unfollow")
    public ResponseEntity<?> unfollowUser(@PathVariable Long userId, @RequestParam Long followerId) {
        userFollowService.unfollowUser(userId, followerId);
        return ResponseEntity.ok("User unfollowed successfully");
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<?> listFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(userFollowService.listFollowers(userId));
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<?> listFollowing(@PathVariable Long userId) {
        return ResponseEntity.ok(userFollowService.listFollowing(userId));
    }
}
