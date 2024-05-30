package com.varun.mobile.insight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.varun.mobile.insight.common.Constants.MESSAGE;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<Map<String, String>> handleUserCreation(UserCreationException exception) {
        Map<String, String> map =  new HashMap<>();
        map.put(MESSAGE, exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<Map<String, String>> handleUserUpdate(UserUpdateException exception) {
        Map<String, String> map =  new HashMap<>();
        map.put(MESSAGE, exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
