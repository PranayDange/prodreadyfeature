package com.example.learnprodreadyfeature.prodreadyfeature.utils;

import com.example.learnprodreadyfeature.prodreadyfeature.dto.PostDto;
import com.example.learnprodreadyfeature.prodreadyfeature.entities.User;
import com.example.learnprodreadyfeature.prodreadyfeature.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSecurity {

    private final PostService postService;

    public boolean isOwnerOfPost(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PostDto postById = postService.getPostById(postId);
        return postById.getAuthor().getId().equals(user.getId());

    }
}
