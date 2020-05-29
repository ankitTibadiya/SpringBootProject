package com.arthur.blogP.service;

import com.arthur.blogP.dto.CommentDto;
import com.arthur.blogP.exception.SpringBlogPException;
import com.arthur.blogP.mapper.CommentMapper;
import com.arthur.blogP.model.Comment;
import com.arthur.blogP.model.NotificationEmail;
import com.arthur.blogP.model.Post;
import com.arthur.blogP.model.User;
import com.arthur.blogP.repository.CommentRepository;
import com.arthur.blogP.repository.PostRepository;
import com.arthur.blogP.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void createComment(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new SpringBlogPException("No post found..!!"));
        Comment comment = commentMapper.map(commentDto,post,authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername()+" posted comment on your post.");
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendEmail(new NotificationEmail(user.getUsername()+" commented on your post", user.getEmail(), message));
    }

    public List<CommentDto> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new SpringBlogPException("Post not found..!!"));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    public List<CommentDto> getCommentsByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringBlogPException("User Not found..!!"));
        return commentRepository.findByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
