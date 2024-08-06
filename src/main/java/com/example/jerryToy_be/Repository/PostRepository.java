package com.example.jerryToy_be.Repository;

import com.example.jerryToy_be.Entity.Destination;
import com.example.jerryToy_be.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(long postId);
    @Query(value = "SELECT p FROM Post p WHERE p.tag = :tag")
    List<Post> findAllByTag(@Param("tag") String tag);
}
