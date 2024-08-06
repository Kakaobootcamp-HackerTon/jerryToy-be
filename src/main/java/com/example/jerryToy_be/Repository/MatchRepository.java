package com.example.jerryToy_be.Repository;

import com.example.jerryToy_be.Entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Match findByUserId(Long userId);
    Match findAllByUserId(Long userId);
}
