package com.springbootblogproject.service;

import com.springbootblogproject.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId,CommentDto commentDto);
}
