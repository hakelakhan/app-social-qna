package com.lakhan.learning.dao;

import com.lakhan.learning.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionDao extends JpaRepository<Reaction, Long> {
    // Additional query methods can be added here if needed
}
