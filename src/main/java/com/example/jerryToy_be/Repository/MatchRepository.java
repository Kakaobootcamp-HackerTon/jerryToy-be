package com.example.jerryToy_be.Repository;

import com.example.jerryToy_be.Entity.Matching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Matching, Long> {
    Matching findByUserId(Long userId);
    Matching findAllByUserId(Long userId);
}
