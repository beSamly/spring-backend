package com.backendapi.advice;

import com.backendapi.dto.ErrorResponse;
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
        /*TODO 런타임 exception은 로깅 or 메세지 alert*/
        ResponseEntity<Object> entity = new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleExceptions(CustomException exception, WebRequest webRequest) {
        ResponseEntity<Object> entity = new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Object> handleExceptions(TransactionSystemException exception, WebRequest webRequest) {
        ResponseEntity<Object> entity = new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
        return entity;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(new ErrorResponse(errorMessage), HttpStatus.BAD_REQUEST);
    }
}