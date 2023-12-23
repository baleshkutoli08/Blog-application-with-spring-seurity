package com.demo.controller;

import com.demo.payload.PostDto;
import com.demo.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class postController {
    private PostService postService;

    public postController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> CreatePost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto Dto = postService.CreatePost(postDto);
        return new  ResponseEntity<>(Dto, HttpStatus.CREATED);

    }@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeletePost(@PathVariable long id){
        postService.DeletePost( id );

        return new  ResponseEntity<>("post id deleted", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    //http:/localhost:8080/api/posts?pageNo=0pageSize=5&sortBy=title&sortDir=desc
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPost(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "sortBy",defaultValue = "3",required = false) String sortBy,
            @RequestParam(name = "pageSize",defaultValue = "id",required = false) int pageSize,
            @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir

            ) {
        List<PostDto> postDtos = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }
    //http:/localhost:8080/api/api/posts?postId=1
    @PreAuthorize("hasRole('ADMIN')")
@PutMapping
    public ResponseEntity<PostDto> updatepost(
            @RequestParam("postId")long postId,
            @RequestBody PostDto postDto
){
       PostDto dto= postService.updatePost(postId,postDto);

    return new ResponseEntity<>(dto,HttpStatus.OK);
    }


}

