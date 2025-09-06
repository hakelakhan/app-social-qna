package com.lakhan.learning.dao;

import com.lakhan.learning.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {
    // Additional query methods can be added here if needed
}
