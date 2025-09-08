package com.lakhan.learning.dao;

import com.lakhan.learning.dtos.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface InterestDao extends JpaRepository<Interest, Long> {
    @Query("SELECT i FROM Interest i ORDER BY SIZE(i.users) DESC LIMIT 10")
    List<Interest> findTop10ByUsersCount();

    boolean existsByName(String interestName);
}

