package com.demo.service;

import com.demo.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto CreatePost(PostDto postDto);

   public void DeletePost(long id);


   public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

     public PostDto updatePost(long postId, PostDto postDto);
}
