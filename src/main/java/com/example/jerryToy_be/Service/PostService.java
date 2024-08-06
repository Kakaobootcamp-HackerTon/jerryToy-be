package com.example.jerryToy_be.Service;

import com.example.jerryToy_be.DTO.PostRequestDTO;
import com.example.jerryToy_be.DTO.PostResponseDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final DestRepository destRepository;
    private final PostResponseDTO postResponseDTO;
    public ResponseEntity submitPost(PostSubmitDTO postSubmitDTO, Long userId){
        try{
            Optional<User> writer = Optional.ofNullable(userRepository.findByUserId(userId));
            if(writer.isPresent()){
                Optional<Destination> dest = Optional.ofNullable(destRepository.findByDestName(postSubmitDTO.getDestName()));
                if(dest.isPresent()){
                    postRepository.save(postSubmitDTO.toEntity(writer.get(), dest.get()));
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<PostResponseDTO> getPostById(Long postId){
        try{
            Optional<Post> post = Optional.ofNullable(postRepository.findByPostId(postId));
            if(post.isPresent()){
                return ResponseEntity
                        .ok()
                        .body(postResponseDTO.byEntity(post.get()));
            } else{
                return ResponseEntity.notFound().build();
            }
        } catch(RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<List<PostResponseDTO>> getAllPost(){
        try{
            Optional<List<Post>> posts = Optional.ofNullable(postRepository.findAll());
            if(posts.isPresent()){
                List<PostResponseDTO> postResponseDTOs = new ArrayList<>();
                for(Post post : posts.get()){
                    if(post.isValid()){
                        postResponseDTOs.add(postResponseDTO.byEntity(post));
                    }
                }
                return ResponseEntity.ok().body(postResponseDTOs);
            } else{
                return ResponseEntity.noContent().build();
            }
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<List<PostResponseDTO>> searchByLabel(String[] label){
        try{
            List<PostResponseDTO> postResponseDTOs = new ArrayList<>();
            for(String tag : label){
                Optional<List<Post>> postList = Optional.ofNullable(postRepository.findAllByTag(tag));
                if(postList.isPresent()){
                    for(Post post : postList.get()){
                        if(post.isValid()){
                            postResponseDTOs.add(postResponseDTO.byEntity(post));
                        }
                    }
                } else {
                    continue;
                }
            }
            return ResponseEntity.ok().body(postResponseDTOs);
        } catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity editPost(PostRequestDTO postRequestDTO, Long postId, Long userId){
        try{
            Optional<Post> post = Optional.ofNullable(postRepository.findByPostId(postId));
            Optional<Destination> dest = Optional.ofNullable(destRepository.findByDestName(postRequestDTO.getDestName()));
            Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId));
            if(post.isPresent()){
                if(dest.isPresent()){
                    post.get().updatePost(postRequestDTO, dest.get());
                    postRepository.save(post.get());
                    if(user.isPresent()){
                        user.get().matchUser(user.get());
                    }
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
