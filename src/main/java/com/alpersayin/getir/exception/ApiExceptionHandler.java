package com.alpersayin.getir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleApiException(ApiRequestException e) {
        return new ResponseEntity<>(buildApiErrorResponse(e, BAD_REQUEST), BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> handleApiException(UsernameNotFoundException e) {
        return new ResponseEntity<>(buildApiErrorResponse(e, NOT_FOUND), NOT_FOUND);
    }

    @ExceptionHandler(value = ApiNotFoundException.class)
    public ResponseEntity<Object> handleApiException(ApiNotFoundException e) {
        return new ResponseEntity<>(buildApiErrorResponse(e, NOT_FOUND), NOT_FOUND);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleApiException(ConstraintViolationException e) {
        return new ResponseEntity<>(buildApiErrorResponse(e, INTERNAL_SERVER_ERROR), INTERNAL_SERVER_ERROR);
    }

    private ApiErrorResponse buildApiErrorResponse(Exception e, HttpStatus httpStatus) {
        return ApiErrorResponse.builder()
                .message(e.getMessage())
                .error(httpStatus.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }

}
