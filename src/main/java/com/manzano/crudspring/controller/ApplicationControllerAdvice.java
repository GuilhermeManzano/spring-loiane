package com.manzano.crudspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import exception.RecordNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
  
  @ExceptionHandler(RecordNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleException(Exception ex) {
    return ex.getMessage();
  }
}
