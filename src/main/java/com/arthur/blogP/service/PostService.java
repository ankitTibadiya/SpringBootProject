package com.arthur.blogP.service;

import com.arthur.blogP.dto.PostDto;
import com.arthur.blogP.dto.PostResponse;
import com.arthur.blogP.exception.SpringBlogPException;
import com.arthur.blogP.mapper.PostMapper;
import com.arthur.blogP.model.Post;
import com.arthur.blogP.model.Subreddit;
import com.arthur.blogP.model.User;
import com.arthur.blogP.repository.PostRepository;
import com.arthur.blogP.repository.SubredditRepository;
import com.arthur.blogP.repository.UserRepository;
import com.arthur.blogP.exception.PostNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Transactional
    public void createPost(PostDto postDto){
        Subreddit subreddit = subredditRepository.findByName(postDto.getSubredditName())
                .orElseThrow(() -> new SpringBlogPException("No Subreddit found with name: " + postDto.getSubredditName()));
        User currentUser = authService.getCurrentUser();
        Post post = postMapper.map(postDto, subreddit, currentUser);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public Object getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("No post found with id- "+id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId){
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SpringBlogPException("No Subreddit found with id: " + subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username){
        com.arthur.blogP.model.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringBlogPException("No user found with name: " + username));
        List<Post> posts = postRepository.findAllByUser(user);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }
}
