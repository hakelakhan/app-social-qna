package com.lakhan.learning.dao;

import com.lakhan.learning.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends JpaRepository<Group, Long> {
    // Additional query methods can be added here if needed
}

