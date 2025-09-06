package com.lakhan.learning.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // Type of reaction (e.g., "LIKE", "LOVE", "HAHA")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // Post the reaction belongs to

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy; // User who reacted

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment; // Comment the reaction belongs to (optional, can be null if it's for a post)
}
