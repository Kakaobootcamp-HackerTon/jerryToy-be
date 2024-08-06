package com.example.jerryToy_be.Service;

import com.example.jerryToy_be.DTO.UserResponseDTO;
import com.example.jerryToy_be.Entity.LikePost;
import com.example.jerryToy_be.Entity.Post;
import com.example.jerryToy_be.Entity.User;
import com.example.jerryToy_be.Repository.LikeRepository;
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
public class LikeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final UserResponseDTO userResponseDTO;
    public ResponseEntity doLike(Long userId, Long postId){
        try{
            Optional<LikePost> like = Optional.ofNullable(likeRepository.findByUserIdAndPostId(userId, postId));
            if(like.isPresent()){
                like.get().updateLike(true);
                likeRepository.save(like.get());
            } else {
                Optional<User> user = userRepository.findById(userId);
                if(user.isPresent()){
                    Optional<Post> post = postRepository.findById(postId);
                    if(post.isPresent()){
                        likeRepository.save(LikePost
                                .builder()
                                .user(user.get())
                                .post(post.get())
                                .build());
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        } catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
        return null;
    }
    public ResponseEntity doUnLike(Long userId, Long postId){
        try{
            Optional<LikePost> like = Optional.ofNullable(likeRepository.findByUserIdAndPostId(userId, postId));
            if(like.isPresent()){
                like.get().updateLike(false);
                likeRepository.save(like.get());
                return ResponseEntity.ok().build();
            } else {
                ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
        return null;
    }
    public ResponseEntity<List<UserResponseDTO>> getLikes(Long postId){
        try{
            Optional<List<LikePost>> likes = Optional.ofNullable(likeRepository.findAllByPostId(postId));
            List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
            if(likes.isPresent()){
                for(LikePost like: likes.get()){
                    userResponseDTOS.add(userResponseDTO.byEntity(like.getUser()));
                }
                return ResponseEntity.ok(userResponseDTOS);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
