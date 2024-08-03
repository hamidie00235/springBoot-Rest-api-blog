package com.springbootblogproject.service.impl;

import com.springbootblogproject.entity.Post;
import com.springbootblogproject.payload.PostDto;
import com.springbootblogproject.repository.PostRepository;
import com.springbootblogproject.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {
    private PostRepository postRepository;

    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert dto entity

        Post post = maptoEntity(postDto);
       Post newPost =postRepository.save(post);

       //convert entity dto

        PostDto postResponse = mapToDTO(newPost);

        return  postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
     return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());


    }
    // convert entity to dto
    private PostDto mapToDTO(Post post){
        PostDto postDto =new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }
    // convert Dto into Entity
    private Post maptoEntity(PostDto postDto){
    Post post =new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
