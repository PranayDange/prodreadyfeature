package com.example.learnprodreadyfeature.prodreadyfeature.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
