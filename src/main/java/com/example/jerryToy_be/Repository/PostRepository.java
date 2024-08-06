package com.example.jerryToy_be.Repository;

import com.example.jerryToy_be.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(long postId);
    Post findByTag(String tag);
}
