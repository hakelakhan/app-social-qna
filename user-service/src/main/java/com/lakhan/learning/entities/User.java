package com.lakhan.learning.entities;

import com.lakhan.learning.dtos.Interest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Core identity
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 30)
    private String username; // e.g. @lakhan

    private String name;

    private String profilePictureUrl;

    @Column(length = 200)
    private String bio; // short description: "Movie lover, gym rat, and startup dreamer"

    // Interests: movie, fitness, career, coding, family, etc.
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_interests", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)  // Store as "MOVIES" instead of ordinal (0,1,2…)
    @Column(name = "interest")
    private Set<Interest> interests = new HashSet<>();

    // Social aspects
    private int followersCount = 0;
    private int followingCount = 0;

    // Posts / comments / reactions can be separate entities linked to user
    private int postsCount = 0;
    private int commentsCount = 0;

    // Activity / fun metrics
    private int points = 0;        // gamification score
    private int badgesCount = 0;   // badges for milestones (e.g., "Funny Commenter")

    // Profile visibility / status
    private boolean onboarded = false; // true after first login wizard
    private boolean isActive = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private Set<Group> groups = new HashSet<>(); // Groups the user is part of

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_followers",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<User> followers = new HashSet<>(); // Users following this user

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_following",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private Set<User> following = new HashSet<>(); // Users this user is following
}
