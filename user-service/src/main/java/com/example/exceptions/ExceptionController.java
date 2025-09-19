package com.example.exceptions;

import com.example.dto.responses.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ResponseHandler<String>> alreadyExistsExceptionHandler(AlreadyExistsException alreadyExistsException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseHandler.<String>builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST)
                        .message(alreadyExistsException.getMessage())
                        .build()
                );
    }


    @ExceptionHandler(EmptyRequestException.class)
    public ResponseEntity<ResponseHandler<String>> emptyRequestExceptionHandler(EmptyRequestException emptyRequestException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseHandler.<String>builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST)
                        .message(emptyRequestException.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseHandler<String>> notFoundExceptionHandler (NotFoundException notFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseHandler.<String>builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .status(HttpStatus.NOT_FOUND)
                        .message(notFoundException.getMessage())
                        .build());
    }
}
