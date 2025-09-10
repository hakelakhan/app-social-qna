package com.lakhan.learning.dtos;

import com.lakhan.learning.entities.Interest;
import com.lakhan.learning.entities.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOnboardingResponse {
    private Long id;
    private String email;
    private String username;
    private String name;
    private String bio;
    private Set<Interest> interests;
    private boolean onboarded;
    private boolean isActive;
    private int followersCount;
    private int followingCount;
    private int postsCount;
    private int commentsCount;
    private int points;
    private int badgesCount;
    private LocalDateTime createdAt;
    private String jwt;

    public static UserOnboardingResponse fromUser(User user) {
        return UserOnboardingResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .name(user.getName())
                .bio(user.getBio())
                .interests(user.getInterests())
                .onboarded(user.isOnboarded())
                .isActive(user.isActive())
                .followersCount(user.getFollowersCount())
                .followingCount(user.getFollowingCount())
                .postsCount(user.getPostsCount())
                .commentsCount(user.getCommentsCount())
                .points(user.getPoints())
                .badgesCount(user.getBadgesCount())
                .createdAt(user.getCreatedAt())
                .build();
    }
}

