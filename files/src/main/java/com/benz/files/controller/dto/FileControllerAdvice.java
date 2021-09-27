package com.benz.files.controller.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class FileControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StatusResponseDTO> handleContentNotAllowedException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(StatusResponseDTO.failed(ex.getBindingResult().getAllErrors().get(0).toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StatusResponseDTO> mismatch(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(StatusResponseDTO.failed(ex.getMessage()), HttpStatus.NOT_FOUND);
    }


}
