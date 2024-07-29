package com.example.learnprodreadyfeature.prodreadyfeature.repositories;

import com.example.learnprodreadyfeature.prodreadyfeature.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
