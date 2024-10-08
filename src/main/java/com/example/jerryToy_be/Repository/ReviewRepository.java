package com.example.jerryToy_be.Repository;

import com.example.jerryToy_be.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByTargetId(Long targetId);
}
