package com.demo.service.impl;


import com.demo.Entity.comment;
import com.demo.Entity.post;
import com.demo.Exception.ResourceNotFoundException;
import com.demo.payload.CommentDto;
import com.demo.repositry.Commentrepositry;
import com.demo.repositry.repositry;
import com.demo.service.CommentSrevice;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class commentImpl implements CommentSrevice {
    private repositry postRepo;
    private Commentrepositry commentRepo;

    public commentImpl(repositry postRepo, Commentrepositry commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDto createComment(long post_id, CommentDto commentDto) {
       post post= postRepo.findById(post_id).orElseThrow(
                ()-> new ResourceNotFoundException("post not found with id"+ post_id)
        );
        comment com= new comment();
        com.setName(commentDto.getName());
        com.setEmail(commentDto.getEmail());
        com.setBody(commentDto.getBody());

        com.setPost(post);
        comment savedComment= commentRepo.save(com);
        CommentDto dto=new CommentDto();



        dto.setId(savedComment.getComment_id());
        dto.setName(savedComment.getName());
        dto.setEmail(savedComment.getEmail());
        dto.setBody(savedComment.getBody());
        return null;
    }

    @Override
    public void deleteComment(long commentId) {
        post post= postRepo.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("post not found with id"+ commentId)
        );
        commentRepo.deleteById(commentId);

    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<comment> comments= commentRepo.findByPostId(postId);
      List<CommentDto> commentDtos=  comments.stream().map(c -> mapTODto(c)).collect(Collectors.toList());
      return commentDtos;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<comment> comment= commentRepo.findAll();
List<CommentDto> dtos=comment.stream().map(c ->mapTODto(c)).collect(Collectors.toList());
        return dtos;
    }

    CommentDto  mapTODto(comment comment){
      CommentDto dto=new CommentDto();
      dto.setId(comment.getComment_id());
      dto.setName(comment.getName());
      dto.setEmail(comment.getEmail());
      dto.setBody(comment.getBody());
      return dto;
  }
}
