package com.benz.files.controller;

import com.benz.files.controller.dto.StatusResponseDTO;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
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
        return new ResponseEntity<>(StatusResponseDTO.failed(ex.getBindingResult().getAllErrors().get(0).toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StatusResponseDTO> mismatch(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(StatusResponseDTO.failed(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<StatusResponseDTO> mismatch(InvalidFormatException ex) {
        return new ResponseEntity<>(StatusResponseDTO.failed(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
