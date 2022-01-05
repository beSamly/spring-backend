package com.backendapi.advice;

import com.backendapi.dto.ExceptionResponse;
import com.backendapi.dto.MyErrResponse;
import com.backendapi.exception.CustomException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleExceptions(RuntimeException exception, WebRequest webRequest) {
        MyErrResponse er = new MyErrResponse("awef",234);
//        er.setMessage("fucking message");
//        er.setStatus(199);
        ResponseEntity<Object> entity = new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
        return entity;
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Object> handleExceptions( RuntimeException exception, WebRequest webRequest) {
//        ExceptionResponse response = new ExceptionResponse();
//        response.setDateTime(LocalDateTime.now());
//        response.setMessage("please work");
//        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
//        return entity;
//    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleExceptions(CustomException customException, WebRequest webRequest) {
//        ExceptionResponse response = new ExceptionResponse();
//        response.setDateTime(LocalDateTime.now());
//        response.setMessage("fucking exception");

        ResponseEntity<Object> entity = new ResponseEntity<>("this is custom exception so dont worry", HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<String> handleExceptions(TransactionSystemException validationException, WebRequest webRequest) {
        ResponseEntity<String> entity = new ResponseEntity<>("this is TransactionSystemException exception", HttpStatus.NOT_FOUND);
        return entity;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(errorMessage, status);
    }
}