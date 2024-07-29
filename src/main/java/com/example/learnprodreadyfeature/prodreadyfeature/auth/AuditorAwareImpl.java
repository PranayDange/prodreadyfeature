package com.example.learnprodreadyfeature.prodreadyfeature.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //get security context
        //get the authentication
        //get the principle
        //get the username
        return Optional.of("pranay dange");
    }
}
