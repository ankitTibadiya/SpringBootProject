package com.arthur.blogP.repository;

import com.arthur.blogP.model.Post;
import com.arthur.blogP.model.User;
import com.arthur.blogP.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
