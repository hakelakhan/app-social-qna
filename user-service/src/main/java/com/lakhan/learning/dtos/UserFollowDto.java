package com.lakhan.learning.dtos;

import com.lakhan.learning.entities.User;
import lombok.Data;

@Data
public class UserFollowDto {
    private Long id;
    private String username;
    private String name;
    private String profilePictureUrl;

    public static UserFollowDto fromUser(User user) {
        UserFollowDto dto = new UserFollowDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setProfilePictureUrl(user.getProfilePictureUrl());
        return dto;
    }
}

