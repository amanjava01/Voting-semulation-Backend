package com.voting_semulation.globlalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String,Object>> handleApiException(ApiException ae){


        Map<String ,Object> error= new HashMap<>();

        error.put("timestamp", LocalDateTime.now());
        error.put("status",ae.getStatus().value());
        error.put("code",ae.getCode());
        error.put("message",ae.getMessage());
        return new ResponseEntity<>(error,ae.getStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handlevalidationException(MethodArgumentNotValidException me){


        Map<String , Object> error=new HashMap<>();

        error.put("timestamp",LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("code","validation_error");
        error.put("Message",me.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleGeneralException(Exception e){

         Map<String,Object> error = new HashMap<>();
         error.put("timestamp",LocalDateTime.now());
            error.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.put("code","internal_server_error");
            error.put("message",e.getMessage());
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);


    }



}
