package com.springbootblogproject.service;

import com.springbootblogproject.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto>getAllPosts();
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto,long id);
}
