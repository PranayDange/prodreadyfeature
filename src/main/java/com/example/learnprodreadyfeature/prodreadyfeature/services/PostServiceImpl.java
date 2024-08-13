package com.example.learnprodreadyfeature.prodreadyfeature.services;

import com.example.learnprodreadyfeature.prodreadyfeature.dto.PostDto;
import com.example.learnprodreadyfeature.prodreadyfeature.entities.PostEntity;
import com.example.learnprodreadyfeature.prodreadyfeature.entities.User;
import com.example.learnprodreadyfeature.prodreadyfeature.exceptions.ResourceNotFoundException;
import com.example.learnprodreadyfeature.prodreadyfeature.repositories.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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
       User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       log.error("User {}",user);
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post id not found" + postId));
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto inputPost, Long postId) {
      PostEntity olderPost=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post id not found" + postId));
        inputPost.setId(postId);
        modelMapper.map(inputPost,olderPost);
        PostEntity savePostEntity=postRepository.save(olderPost);
        return modelMapper.map(savePostEntity,PostDto.class);
    }
}
