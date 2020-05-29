package com.arthur.blogP.controller;

import com.arthur.blogP.model.Post;
import com.arthur.blogP.dto.PostDto;
import com.arthur.blogP.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/")
    public ResponseEntity createPost(@RequestBody PostDto postDto){
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllPosts(){
        return new ResponseEntity(postService.showAllPosts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id){
        return new ResponseEntity(postService.getPostById(id),HttpStatus.OK);
    }

    @GetMapping("/by-subreddit/{id}")
    public ResponseEntity<List<Post>> getPostsBySubreddit(@PathVariable Long id){
        return new ResponseEntity(postService.getPostsBySubreddit(id),HttpStatus.OK);
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable String username){
        return new ResponseEntity(postService.getPostsByUsername(username),HttpStatus.OK);
    }
}
