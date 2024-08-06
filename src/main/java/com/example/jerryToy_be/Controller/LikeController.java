package com.example.jerryToy_be.Controller;

import com.example.jerryToy_be.DTO.UserResponseDTO;
import com.example.jerryToy_be.Service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;
    @PostMapping("/like/{postId}")
    public ResponseEntity doLike(@PathVariable Long postId, @AuthenticationPrincipal Long userId){
        try{
            return likeService.doLike(userId, postId);
        } catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/like/{postId}")
    public ResponseEntity<List<UserResponseDTO>> getLike(@PathVariable Long postId){
        try{
            return likeService.getLikes(postId);
        } catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/unlike/{postId}")
    public ResponseEntity doUnLike(@PathVariable Long postId, @AuthenticationPrincipal Long userId){
        try{
            return likeService.doUnLike(userId, postId);
        } catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
