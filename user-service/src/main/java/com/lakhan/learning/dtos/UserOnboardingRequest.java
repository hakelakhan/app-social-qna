package com.lakhan.learning.dtos;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOnboardingRequest {
    private String email;
    private String username;
    private String name;
    private String bio;
    private Set<Interest> interests;
}

