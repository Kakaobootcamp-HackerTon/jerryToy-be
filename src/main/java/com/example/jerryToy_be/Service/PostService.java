package com.example.jerryToy_be.Service;

import com.example.jerryToy_be.DTO.PostSubmitDTO;
import com.example.jerryToy_be.Entity.Destination;
import com.example.jerryToy_be.Entity.Post;
import com.example.jerryToy_be.Entity.User;
import com.example.jerryToy_be.Repository.DestRepository;
import com.example.jerryToy_be.Repository.PostRepository;
import com.example.jerryToy_be.Repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final DestRepository destRepository;
    public ResponseEntity submitPost(PostSubmitDTO postSubmitDTO) throws RuntimeException {
        User writer = userRepository.findByUserId(postSubmitDTO.getUserId());
        Destination dest = destRepository.findByDestName(postSubmitDTO.getDestName());
        postRepository.save(postSubmitDTO.toEntity(writer, dest));
        return ResponseEntity
                .ok()
                .build();
    }
    public ResponseEntity<Post> getPostById(Long postId) throws RuntimeException {
        Post post = postRepository.findByPostId(postId);
        return ResponseEntity
                .ok()
                .body(post);
    }
    public ResponseEntity<List<Post>> getAllPost() throws RuntimeException {
        List<Post> posts = postRepository.findAll();
        return ResponseEntity
                .ok()
                .body(posts);
    }
}
