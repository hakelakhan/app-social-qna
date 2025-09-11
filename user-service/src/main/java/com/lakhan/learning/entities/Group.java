package com.lakhan.learning.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString(exclude = "members, createdBy, groupInterests")
@ToString(exclude = {"members", "createdBy", "groupInterests"})
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Group name

    @Column(length = 500)
    private String description; // Group description

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    @JsonIgnore
    private User createdBy; // User who created the group

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "group_members",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private Set<User> members = new HashSet<>(); // Users who are members of the group

    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "profile_pic_path")
    private String profilePicPath; // Path to the group's profile picture

    @Column(name = "group_type", nullable = false)
    private String groupType; // Group type: "PRIVATE" or "PUBLIC"

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "group_interest_mapping",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    @JsonIgnore
    private Set<Interest> groupInterests = new HashSet<>(); // Tags for categorizing the group
}
