package com.awesome.pizza.gestione_ordini.service.validator.exception;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {

  private final List<ErrorMessage> messages;

  public ValidationException(List<ErrorMessage> messages) {
    this.messages = messages;
  }

  @Override
  public String getMessage() {
    return messages
        .stream()
        .map(errorMessage -> errorMessage.campo() + ":" + errorMessage.message())
        .collect(Collectors.joining("; "));
  }

  public List<ErrorMessage> getMessages() {
    return messages;
  }
}
