package com.example.jerryToy_be.Controller;

import com.example.jerryToy_be.DTO.PostRequestDTO;
import com.example.jerryToy_be.DTO.PostResponseDTO;
import com.example.jerryToy_be.DTO.PostSubmitDTO;
import com.example.jerryToy_be.Entity.Post;
import com.example.jerryToy_be.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostsController {
    private PostService postService;
    @GetMapping("/all")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(){
        try{
            ResponseEntity<List<PostResponseDTO>> res = postService.getAllPost();
            return res;
        } catch(RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getOnePost(@PathVariable Long postId){
        if(postId==null){
            return ResponseEntity.badRequest().build();
        }
        try{
            ResponseEntity<PostResponseDTO> res = postService.getPostById(postId);
            return res;
        } catch(RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/all")
    public ResponseEntity<List<PostResponseDTO>> getPostsByTag(@RequestBody String[] tag){
        if(tag==null){
            return ResponseEntity.badRequest().build();
        }
        try{
            return postService.searchByLabel(tag);
        } catch(RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/submit")
    public ResponseEntity<Post> submitPost(@RequestBody PostSubmitDTO post, @AuthenticationPrincipal Long userId){
        // userId 받아오기
        if(post==null){
            return ResponseEntity.badRequest().build();
        }
        try{
            ResponseEntity res = postService.submitPost(post, userId);
            return res;
        }catch(RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PatchMapping("/{postId}")
    public ResponseEntity editPost(@PathVariable Long postId, @RequestBody PostRequestDTO postRequestDTO){
        try{
            return postService.editPost(postRequestDTO, postId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
