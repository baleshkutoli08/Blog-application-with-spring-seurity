package com.demo.controller;

import com.demo.payload.CommentDto;
import com.demo.service.CommentSrevice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class commentController {
    public commentController(CommentSrevice commentSrevice) {
        this.commentSrevice = commentSrevice;
    }

    private CommentSrevice commentSrevice;



    @PostMapping
    public ResponseEntity<CommentDto> createComments(
            @RequestParam("postId") long postId, @RequestBody CommentDto commentDto
    ){
            CommentDto dto= commentSrevice.createComment(postId,commentDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComments(@PathVariable long  commentId){
        System.out.println(commentId);
       commentSrevice.deleteComment(commentId);
       return new ResponseEntity<>("comment is deleted",HttpStatus.OK);

    }
    @GetMapping("/{postid}")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable long postid){
        System.out.println(postid);
       List<CommentDto> commentDtos= commentSrevice.getCommentByPostId (postid);
return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){

        List<CommentDto> commentDtos= commentSrevice.getAllComments ();
        return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }
}
