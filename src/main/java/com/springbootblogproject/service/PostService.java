package com.springbootblogproject.service;

import com.springbootblogproject.payload.PostDto;
import com.springbootblogproject.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto,long id);
    void deletePostById(Long id);
}
