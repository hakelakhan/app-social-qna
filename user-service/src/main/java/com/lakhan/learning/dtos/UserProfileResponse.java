package com.lakhan.learning.dtos;


import com.lakhan.learning.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserProfileResponse {

    private boolean isNewUser;
    private Long id;
    private String email;
    private String username;
    private String name;
    private String profilePictureUrl;
    private String bio;

    private Set<String> interests;

    private int followersCount;
    private int followingCount;
    private int postsCount;
    private int commentsCount;

    private int points;
    private int badgesCount;

    private boolean onboarded;
    private boolean active;

    private LocalDateTime createdAt;

    // Mapper method
    public static UserProfileResponse fromUser(User user) {
            return UserProfileResponse.builder()
                    .isNewUser(false)                        // Existing user
                    .id(user.getId())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .name(user.getName())
                    .profilePictureUrl(user.getProfilePictureUrl())
                    .bio(user.getBio())
                    .interests(user.getInterests() != null
                            ? user.getInterests().stream()
                            .map(Enum::name)         // Convert enum to String
                            .collect(Collectors.toSet())
                            : null)
                    .followersCount(user.getFollowersCount())
                    .followingCount(user.getFollowingCount())
                    .postsCount(user.getPostsCount())
                    .commentsCount(user.getCommentsCount())
                    .points(user.getPoints())
                    .badgesCount(user.getBadgesCount())
                    .onboarded(user.isOnboarded())
                    .active(user.isActive())
                    .createdAt(user.getCreatedAt())
                    .build();
    }

}

