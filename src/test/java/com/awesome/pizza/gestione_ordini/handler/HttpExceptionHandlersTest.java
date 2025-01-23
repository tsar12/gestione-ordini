package com.awesome.pizza.gestione_ordini.handler;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import com.awesome.pizza.gestione_ordini.UnitTest;
import com.awesome.pizza.gestione_ordini.handler.model.ErrorMessageDto;
import com.awesome.pizza.gestione_ordini.service.validator.exception.ErrorMessage;
import com.awesome.pizza.gestione_ordini.service.validator.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;

class HttpExceptionHandlersTest extends UnitTest {

  @InjectMocks
  private HttpExceptionHandlers handlers;

  @Test
  void handleValidationMessagesException_catturaEccezione_ritornaReponseConErrori() {
    ResponseEntity<ErrorMessageDto> responseEntity = handlers
        .handleValidationMessagesException(
            new ValidationException(asList(new ErrorMessage("field", "errore"))));

    assertThat(responseEntity.getStatusCode().is4xxClientError()).isTrue();
    assertThat(responseEntity.getBody().getMessage()).isEqualTo("field:errore");
  }
}