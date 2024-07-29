package com.example.learnprodreadyfeature.prodreadyfeature.services;

import com.example.learnprodreadyfeature.prodreadyfeature.dto.PostDto;
import com.example.learnprodreadyfeature.prodreadyfeature.entities.PostEntity;
import com.example.learnprodreadyfeature.prodreadyfeature.exceptions.ResourceNotFoundException;
import com.example.learnprodreadyfeature.prodreadyfeature.repositories.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream().map(postEntity -> modelMapper.map(postEntity, PostDto.class)).toList();
    }

    @Override
    public PostDto createNewPost(PostDto inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDto.class);

    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post id not found" + postId));
        return modelMapper.map(postEntity, PostDto.class);
    }
}
