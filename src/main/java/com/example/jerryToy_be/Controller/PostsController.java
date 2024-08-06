package com.example.jerryToy_be.Controller;

import com.example.jerryToy_be.DTO.PostSubmitDTO;
import com.example.jerryToy_be.Entity.Post;
import com.example.jerryToy_be.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostsController {
    private PostService postService;
    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts(){
        try{
            ResponseEntity<List<Post>> res = postService.getAllPost();
            return res;
        } catch(RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getOnePost(@PathVariable Long postId){
        if(postId==null){
            return ResponseEntity.badRequest().build();
        }
        try{
            ResponseEntity<Post> res = postService.getPostById(postId);
            return res;
        } catch(RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/submit")
    public ResponseEntity<Post> submitPost(@RequestBody PostSubmitDTO post){
        if(post==null){
            return ResponseEntity.badRequest().build();
        }
        try{
            ResponseEntity res = postService.submitPost(post);
            return res;
        }catch(RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
