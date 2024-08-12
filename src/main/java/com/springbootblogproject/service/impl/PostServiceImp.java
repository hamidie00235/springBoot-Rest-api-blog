package com.springbootblogproject.service.impl;

import com.springbootblogproject.entity.Post;
import com.springbootblogproject.exception.ResourceNotFoundException;
import com.springbootblogproject.payload.PostDto;
import com.springbootblogproject.payload.PostResponse;
import com.springbootblogproject.repository.PostRepository;
import com.springbootblogproject.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

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
    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
        // create Pageable instance
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
       Pageable pageable =PageRequest.of(pageNo,pageSize,sort);

        Page<Post> posts = postRepository.findAll(pageable);
        // get content for page object
        List<Post> listOfPosts=posts.getContent();

    List<PostDto>  content =listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalElements(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
 return postResponse;

    }

    @Override
    public PostDto getPostById(long id) {
        Post post =postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id" ,id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        // get post by id from database
        Post post =postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id" ,id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
     Post updatePost= postRepository.save(post);
        return mapToDTO(updatePost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post =postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id" ,id));
        postRepository.delete(post);
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

