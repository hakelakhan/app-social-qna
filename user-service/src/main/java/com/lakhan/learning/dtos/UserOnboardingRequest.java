package com.lakhan.learning.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOnboardingRequest {
    private String username;
    private String name;
    private String bio;
    private String interests;
    private String profilePictureBase64; // New field for profile picture in Base64 format
    private Long userId;

}

