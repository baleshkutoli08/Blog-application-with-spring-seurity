package com.demo.service;

import com.demo.payload.CommentDto;

import java.util.List;


public interface CommentSrevice {

    public CommentDto createComment(long post_id, CommentDto commentDto);

    void deleteComment(long commentId);

    List<CommentDto> getCommentByPostId(long postId);

    List<CommentDto> getAllComments();
}
