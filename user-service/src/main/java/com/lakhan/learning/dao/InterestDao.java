package com.lakhan.learning.dao;

import com.lakhan.learning.entities.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InterestDao extends JpaRepository<Interest, Long> {
    boolean existsByName(String interestName);

    Optional<Interest> findByName(String name);
}

