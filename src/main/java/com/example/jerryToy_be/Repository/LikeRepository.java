package com.example.jerryToy_be.Repository;

import com.example.jerryToy_be.Entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikePost, Long> {
    public LikePost findByUserIdAndPostId(Long userId, Long postId);
    @Query(value="SELECT l FROM LikePost l WHERE l.post.postId = :postId")
    public List<LikePost> findAllByPostId(Long postId);
}
