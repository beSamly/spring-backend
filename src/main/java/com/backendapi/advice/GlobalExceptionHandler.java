package com.backendapi.advice;

import com.backendapi.constants.RESPONSE_RESULT_TYPE;
import com.backendapi.dto.ErrorResponse;
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

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleExceptions(RuntimeException exception, WebRequest webRequest) {
        /*TODO 런타임 exception은 로깅 or 메세지 alert*/
        ErrorResponse e = new ErrorResponse(RESPONSE_RESULT_TYPE.RUNTIME_EXCEPTION, exception.getMessage());
        return new ResponseEntity<ErrorResponse>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleExceptions(CustomException exception, WebRequest webRequest) {
        ErrorResponse e = new ErrorResponse(exception.getResultType(), exception.getMessage());
        return new ResponseEntity<ErrorResponse>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ErrorResponse> handleExceptions(TransactionSystemException exception, WebRequest webRequest) {
        ErrorResponse e = new ErrorResponse(RESPONSE_RESULT_TYPE.TRANSACTION_SYSTEM_EXCEPTION, exception.getMessage());
        return new ResponseEntity<ErrorResponse>(e, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String field = ex.getBindingResult().getFieldErrors().get(0).getField();
        String defaultMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ErrorResponse e = new ErrorResponse(RESPONSE_RESULT_TYPE.INVALID_INPUT, String.format("%s %s", field, defaultMessage));
        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
    }
}