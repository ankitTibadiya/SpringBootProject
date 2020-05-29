package com.arthur.blogP.controller;

import com.arthur.blogP.dto.SubredditDto;
import com.arthur.blogP.service.SubredditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subreddit")
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.createSubreddit(subredditDto));

    }

    @GetMapping("/all")
    public ResponseEntity<List<SubredditDto>> showAllSubreddit(){
        return new ResponseEntity(subredditService.showAllPosts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubredditById(@PathVariable Long id){
        return new ResponseEntity(subredditService.getSubredditById(id),HttpStatus.OK);
    }
}
