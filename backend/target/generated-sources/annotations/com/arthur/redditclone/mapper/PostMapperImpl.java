package com.arthur.redditclone.mapper;

import com.arthur.blogP.dto.PostDto;
import com.arthur.blogP.dto.PostResponse;
import com.arthur.blogP.mapper.PostMapper;
import com.arthur.blogP.model.Post;
import com.arthur.blogP.model.Post.PostBuilder;
import com.arthur.blogP.model.Subreddit;
import javax.annotation.Generated;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-18T20:39:56-0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post map(PostDto postDto, Subreddit subreddit, User user) {
        if ( postDto == null && subreddit == null && user == null ) {
            return null;
        }

        PostBuilder post = Post.builder();

        if ( postDto != null ) {
            post.description( postDto.getDescription() );
            post.postId( postDto.getPostId() );
            post.postName( postDto.getPostName() );
            post.url( postDto.getUrl() );
        }
        if ( subreddit != null ) {
            post.user( subreddit.getUser() );
        }
        if ( user != null ) {
            post.username( user.getUsername() );
        }
        post.createdDate( java.time.Instant.now() );

        return post.build();
    }

    @Override
    public PostResponse mapToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setId( post.getPostId() );
        postResponse.setUserName( postUserUsername( post ) );
        postResponse.setSubredditName( postSubredditName( post ) );
        postResponse.setPostName( post.getPostName() );
        postResponse.setUrl( post.getUrl() );
        postResponse.setDescription( post.getDescription() );

        return postResponse;
    }

    private String postUserUsername(Post post) {
        if ( post == null ) {
            return null;
        }
        com.arthur.blogP.model.User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String postSubredditName(Post post) {
        if ( post == null ) {
            return null;
        }
        Subreddit subreddit = post.getSubreddit();
        if ( subreddit == null ) {
            return null;
        }
        String name = subreddit.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
