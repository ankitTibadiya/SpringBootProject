package com.arthur.blogP.mapper;

import com.arthur.blogP.dto.CommentDto;
import com.arthur.blogP.model.Comment;
import com.arthur.blogP.model.Comment.CommentBuilder;
import com.arthur.blogP.model.Post;
import com.arthur.blogP.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-20T20:08:10-0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment map(CommentDto commentDto, Post post, User user) {
        if ( commentDto == null && post == null && user == null ) {
            return null;
        }

        CommentBuilder comment = Comment.builder();

        if ( commentDto != null ) {
            comment.text( commentDto.getText() );
        }
        if ( post != null ) {
            comment.user( post.getUser() );
        }
        comment.createdDate( java.time.Instant.now() );

        return comment.build();
    }

    @Override
    public CommentDto mapToDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setId( comment.getId() );
        commentDto.setCreatedDate( comment.getCreatedDate() );
        commentDto.setText( comment.getText() );

        commentDto.setPostId( comment.getPost().getPostId() );
        commentDto.setUsername( comment.getUser().getUsername() );

        return commentDto;
    }
}
