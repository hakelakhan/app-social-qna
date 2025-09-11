package com.lakhan.learning.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreateNewGroupRequest {

    private String name; // Group name

    private String description; // Group description

    private String groupProfilePictureUrl; // URL for the group's profile picture

    private String groupType; // Group type: "PRIVATE" or "PUBLIC"

    private Set<String> interests; // Tags for categorizing the group (e.g., "fitness", "coding")
}