package com.blog.app.controllers;

import com.blog.app.payloads.APIResponse;
import com.blog.app.payloads.CommentDTO;
import com.blog.app.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,
                                                    @PathVariable Integer postId){
        CommentDTO createComment = commentService.createComment(commentDTO, postId);
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<APIResponse> deleteComment(@PathVariable Integer commentId){

        commentService.deleteComment(commentId);
        return new ResponseEntity<APIResponse>(new APIResponse("Comment Deleted!!", true), HttpStatus.OK);
    }

}
