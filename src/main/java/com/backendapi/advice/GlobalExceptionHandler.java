package com.backendapi.advice;

import com.backendapi.exception.CustomException;
import com.backendapi.exception.MyCustomException;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.net.BindException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleExceptions(RuntimeException exception, WebRequest webRequest) {
        ResponseEntity<String> entity = new ResponseEntity<>("fucking runtime exception", HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleExceptions(CustomException customException, WebRequest webRequest) {
//        ExceptionResponse response = new ExceptionResponse();
//        response.setDateTime(LocalDateTime.now());
//        response.setMessage("fucking exception");
        ResponseEntity<String> entity = new ResponseEntity<>("this is custom exception so dont worry", HttpStatus.NOT_FOUND);
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