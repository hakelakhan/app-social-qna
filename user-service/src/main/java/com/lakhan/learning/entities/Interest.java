package com.lakhan.learning.entities;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "interests")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"users", "groups"})
@Builder
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "interests", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users;

    @ManyToMany(mappedBy = "groupInterests", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Group> groups;
    // equals and hashCode based on id for entity identity
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interest interest)) return false;
        return Objects.equals(id, interest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}