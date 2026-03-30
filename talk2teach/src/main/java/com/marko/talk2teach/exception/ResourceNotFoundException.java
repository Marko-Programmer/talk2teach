package com.marko.talk2teach.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
      super(message);
    }
}
