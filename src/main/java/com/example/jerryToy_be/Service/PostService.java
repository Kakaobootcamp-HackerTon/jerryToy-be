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

@Service
@Getter
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final DestRepository destRepository;
    public ResponseEntity submitPost(PostSubmitDTO postSubmitDTO){
        User writer = userRepository.findByUserId(postSubmitDTO.getUserId());
        Destination dest = destRepository.findByDestName(postSubmitDTO.getDestName());
        return postRepository.save(postSubmitDTO.toEntity(writer, dest);
    }
    public Post getPostById(Long postId){
        postRepository.findByPostId(postId);
    }
}
