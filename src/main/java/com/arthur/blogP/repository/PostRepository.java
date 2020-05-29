package com.arthur.blogP.repository;

import com.arthur.blogP.model.Post;
import com.arthur.blogP.model.Subreddit;
import com.arthur.blogP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findAllByUser(User user);
}
