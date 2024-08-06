package com.example.jerryToy_be.Controller;

import com.example.jerryToy_be.Entity.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @GetMapping
    public List<Post> getAllPosts(){

    }
}
