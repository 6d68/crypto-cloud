package com.cryptocloud.currencies;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CurrencyServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<Object> handleItemNotFound(ItemNotFoundException ex, WebRequest request) {
        CurrencyServiceError apiError = new CurrencyServiceError(NOT_FOUND, ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(CurrencyServiceError error) {
        return new ResponseEntity<>(error, error.getStatus());
    }
}