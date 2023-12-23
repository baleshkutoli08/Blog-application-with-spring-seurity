package com.demo.service.impl;

import com.demo.Entity.post;
import com.demo.Exception.ResourceNotFoundException;
import com.demo.payload.PostDto;

import com.demo.repositry.repositry;
import com.demo.service.PostService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class postServiceImpl implements PostService {

    private repositry postRepo;

    public postServiceImpl(repositry postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto CreatePost(PostDto postDto) {
        post Post=new post();
        Post.setTitle(postDto.getTitle());
        Post.setContent(postDto.getContent());
        Post.setDescription(postDto.getDescription());


        post savedPost= postRepo.save(Post);

        PostDto dto=new PostDto();

        dto.setId(savedPost.getId());
        dto.setContent(savedPost.getContent());
        dto.setDescription(savedPost.getDescription());
        dto.setTitle(savedPost.getTitle());


        return dto;


    }

    @Override
    public void DeletePost(long id) {
//        post post=postRepo.deleteById(id).OrElseThrow (
//                ()->new ResourceNotFoundException("post us not found with id :"+id);
//        );
        post post;
        post = postRepo.findById(id).
                orElseThrow(
                        () -> new ResourceNotFoundException("Post not found with id: " + id)
                );

        postRepo.deleteById(id);
//        Optional<post> byId = postRepo.findById(id);
//        if (byId.isPresent()){
//        postRepo.deleteById(id);
//        }else {
//            throw new ResourceNotFoundException("post not found with id"+id);
//        }
    }



    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
      Sort sort=  (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
       Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<post> pagePosts=postRepo.findAll(pageable);
       List<post> posts=pagePosts.getContent();
        List<PostDto> postDtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return postDtos;

    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
       post post= postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post Not Found with id"+postId)
        );

        post.setTitle(  postDto.getTitle());
        post.setContent(post.getContent());
        post.setDescription(postDto.getDescription());
        post saved=postRepo.save(post);
       PostDto dto= mapToDto(saved);
        return dto;
    }

    PostDto  mapToDto(post posts){
      PostDto dto=new PostDto();

      dto.setId(posts.getId());
      dto.setContent(posts.getContent());
      dto.setDescription(posts.getDescription());
      dto.setTitle(posts.getTitle());

      return dto;
  }

}
