package com.springbootblogproject.service.impl;

import com.springbootblogproject.entity.Comment;
import com.springbootblogproject.entity.Post;
import com.springbootblogproject.exception.ResourceNotFoundException;
import com.springbootblogproject.payload.CommentDto;
import com.springbootblogproject.repository.CommentRepository;
import com.springbootblogproject.repository.PostRepository;
import com.springbootblogproject.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository=postRepository;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment =mapToEntity(commentDto);

        //retrieve post entity by id
        Post post=postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id" ,postId));

        // set post to comment entity
        comment.setPost(post);

        // comment entity to db
        Comment newComment =commentRepository.save(comment);

        return mapToDto(newComment);
    }
    // convert entity to dto
    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto =new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
return commentDto;
    }
    // convert dto to entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment =new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
