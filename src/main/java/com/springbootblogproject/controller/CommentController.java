package com.springbootblogproject.controller;

import com.springbootblogproject.payload.CommentDto;
import com.springbootblogproject.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    // comment create rest api
    @PostMapping("/post/{postId}/comments")
  public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long postId ,
                                                  @Valid @RequestBody CommentDto commentDto){

  return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    // get comments rest api
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }
    // get comment rest api by id
@GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
                                                     @PathVariable(value = "id") long commentId){
     CommentDto commentDto=commentService.getCommentById(postId, commentId);
     return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId,
                                                    @PathVariable(value = "id") long commentId,
                                                   @Valid @RequestBody CommentDto commentDto){
        CommentDto updatedComment=commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);

    }
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String>deleteComment(@PathVariable(value = "postId") long postId,
                                               @PathVariable(value = "id") long commentId){

     commentService.deleteComment(postId, commentId);
     return  new ResponseEntity<>("comment deleted successfully",HttpStatus.OK);

    }

}
