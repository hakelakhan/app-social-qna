package com.lakhan.learning.entities;

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
@ToString
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
    private User createdBy; // User who created the group

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "group_members",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> members = new HashSet<>(); // Users who are members of the group

    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "profile_pic_path")
    private String profilePicPath; // Path to the group's profile picture

    @Column(name = "group_type", nullable = false)
    private String groupType; // Group type: "PRIVATE" or "PUBLIC"

    @ElementCollection
    @CollectionTable(name = "group_tags", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "tag")
    private Set<String> tags = new HashSet<>(); // Tags for categorizing the group
}
