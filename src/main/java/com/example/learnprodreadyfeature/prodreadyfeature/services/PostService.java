package com.example.learnprodreadyfeature.prodreadyfeature.services;

import com.example.learnprodreadyfeature.prodreadyfeature.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto inputPost);

    PostDto getPostById(Long postId);
}
