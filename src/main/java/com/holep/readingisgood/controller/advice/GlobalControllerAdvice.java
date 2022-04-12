package com.holep.readingisgood.controller.advice;

import com.holep.readingisgood.domian.ErrorResponse;
import com.holep.readingisgood.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        return new ResponseEntity<>(ErrorResponse.of(e.getCode(), e.getDescription()),
                e.getStatus());
    }
}
