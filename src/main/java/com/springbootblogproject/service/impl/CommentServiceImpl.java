package com.springbootblogproject.service.impl;

import com.springbootblogproject.entity.Comment;
import com.springbootblogproject.entity.Post;
import com.springbootblogproject.exception.BlogApiException;
import com.springbootblogproject.exception.ResourceNotFoundException;
import com.springbootblogproject.payload.CommentDto;
import com.springbootblogproject.repository.CommentRepository;
import com.springbootblogproject.repository.PostRepository;
import com.springbootblogproject.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        // Retrieve post entity by id
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // Set post to comment entity
        comment.setPost(post);

        // Save comment entity to database
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        // Retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        // Convert list of comment entities to list of comment DTOs
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        // Retrieve post entity by id to ensure it exists
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // Retrieve comment by id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Check if the comment belongs to the post
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException("Comment does not belong to post", HttpStatus.BAD_REQUEST);
        }

        return mapToDto(comment);
    }

    // Convert entity to DTO
    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    // Convert DTO to entity
    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
