package com.springbootblogproject.controller;

import com.springbootblogproject.payload.PostDto;
import com.springbootblogproject.payload.PostResponse;
import com.springbootblogproject.service.PostService;
import com.springbootblogproject.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    // create blog  post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto>createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }
  // get all posts rest api
    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value ="pageNo" ,defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                    @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                    @RequestParam (value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
                                    @RequestParam (value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir){
        return postService.getAllPosts(pageNo, pageSize,sortBy,sortDir);
    }
    // get post by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable (name = "id") long id){

        return ResponseEntity.ok(postService.getPostById(id));
    }
    // update post by id rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable long id){
 PostDto postResponse  =postService.updatePost(postDto, id);
 return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    // delete post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable (name = "id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("post entity deleted successfully",HttpStatus.OK);
    }

}
