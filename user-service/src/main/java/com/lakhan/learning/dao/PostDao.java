package com.lakhan.learning.dao;

import com.lakhan.learning.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends JpaRepository<Post, Long> {
    // Additional query methods can be added here if needed
}

