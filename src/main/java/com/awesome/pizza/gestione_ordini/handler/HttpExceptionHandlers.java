package com.awesome.pizza.gestione_ordini.handler;

import com.awesome.pizza.gestione_ordini.handler.model.ErrorMessageDto;
import com.awesome.pizza.gestione_ordini.service.validator.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpExceptionHandlers {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorMessageDto> handleValidationMessagesException(
      ValidationException exception) {
    ErrorMessageDto errorMessageDto = new ErrorMessageDto();
    errorMessageDto.setMessage(exception.getMessage());
    return ResponseEntity.badRequest().body(errorMessageDto);
  }
}
