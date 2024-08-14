package com.springbootblogproject.controller;

import com.springbootblogproject.payload.CommentDto;
import com.springbootblogproject.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                  @RequestBody CommentDto commentDto){

  return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
}
