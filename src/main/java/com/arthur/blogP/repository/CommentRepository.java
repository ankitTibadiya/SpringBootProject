package com.arthur.blogP.repository;

import com.arthur.blogP.model.Post;
import com.arthur.blogP.model.Comment;
import com.arthur.blogP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findByUser(User user);
}
