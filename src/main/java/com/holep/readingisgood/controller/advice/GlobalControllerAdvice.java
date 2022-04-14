package com.holep.readingisgood.controller.advice;

import com.holep.readingisgood.domain.response.ErrorResponse;
import com.holep.readingisgood.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getMessage()),
                e.getStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class, BindException.class})
    public ResponseEntity<ErrorResponse> handleValidationException(ConstraintViolationException e) {
        return new ResponseEntity<>(ErrorResponse.of("400", e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
