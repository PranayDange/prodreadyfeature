package com.example.learnprodreadyfeature.prodreadyfeature.repositories;

import com.example.learnprodreadyfeature.prodreadyfeature.entities.Session;
import com.example.learnprodreadyfeature.prodreadyfeature.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
