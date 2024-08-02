package com.springbootblogproject.service.impl;

import com.springbootblogproject.entity.Post;
import com.springbootblogproject.payload.PostDto;
import com.springbootblogproject.repository.PostRepository;
import com.springbootblogproject.service.PostService;

public class PostServiceImp implements PostService {
    private PostRepository postRepository;

    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert dto entity
        Post post =new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(post.getContent());
       Post newPost =postRepository.save(post);
       //convert entity dto

        PostDto postResponse =new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());
        return  postResponse;
    }
}
