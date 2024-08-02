package com.springbootblogproject.service;

import com.springbootblogproject.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
