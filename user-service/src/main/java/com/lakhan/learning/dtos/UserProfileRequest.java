package com.lakhan.learning.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRequest {

    private String email;
    private String username;
    private String name;
    private String profilePictureUrl;
    private String bio;
    private Set<Interest> interests;
}
