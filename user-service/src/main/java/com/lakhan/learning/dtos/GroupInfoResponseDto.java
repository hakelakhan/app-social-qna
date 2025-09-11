package com.lakhan.learning.dtos;


import com.lakhan.learning.entities.Group;
import com.lakhan.learning.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data // or record in Java 17+
public class GroupInfoResponseDto {
    private Long id;
    private String name;
    private String description;
    private Long createdByUserId;
    private String createdByUsername;
    private Set<Long> memberIds;
    private LocalDateTime createdAt;
    private String profilePicPath;
    private String groupType;
    private Set<String> tags;
    private int memberCount; // optional convenience field
    public static GroupInfoResponseDto toDto(Group group) {
        GroupInfoResponseDto dto = new GroupInfoResponseDto();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setDescription(group.getDescription());

        if (group.getCreatedBy() != null) {
            dto.setCreatedByUserId(group.getCreatedBy().getId());
            dto.setCreatedByUsername(group.getCreatedBy().getUsername());
        }

        dto.setMemberIds(group.getMembers()
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet()));
        dto.setMemberCount(group.getMembers().size());
        dto.setCreatedAt(group.getCreatedAt());
        dto.setProfilePicPath(group.getProfilePicPath());
        dto.setGroupType(group.getGroupType());
        dto.setTags(group.getGroupInterests().stream().map(interest -> interest.getName()).collect(Collectors.toSet()));

        return dto;
    }

}
