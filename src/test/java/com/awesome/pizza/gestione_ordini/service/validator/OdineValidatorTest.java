package com.awesome.pizza.gestione_ordini.service.validator;

import static com.awesome.pizza.gestione_ordini.builders.OrdinePizzaDtoBuilder.anOrdinePizzaDto;
import static com.awesome.pizza.gestione_ordini.builders.OrdinePizzaEntityBuilder.anOrdinePizzaEntity;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.awesome.pizza.gestione_ordini.UnitTest;
import com.awesome.pizza.gestione_ordini.builders.OrdinePizzaDtoBuilder;
import com.awesome.pizza.gestione_ordini.builders.OrdinePizzaEntityBuilder;
import com.awesome.pizza.gestione_ordini.controller.model.dto.OrdinePizzaDto;
import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import com.awesome.pizza.gestione_ordini.service.validator.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class OdineValidatorTest extends UnitTest {
  @InjectMocks
  OdineValidator validator;
  private static final String errore
      = "stato:Stato non valido;"
      + " tipologia:Tipologia ordine non valida;"
      + " codice_ordine:Codice ordine non valido;"
      + " quantita:Quantita deve essere maggiore di 0";
  @Test
  void creaOrdine_oggettoNonValido_rilasciaEccezioneConListaErrori() {
    assertThatThrownBy(
        () ->validator.creaOrdine(new OrdinePizzaDto())
        ).isInstanceOf(ValidationException.class)
        .hasMessage(errore);
    ;
  }
  @Test
  void creaOrdine_oggettoValido_rilasciaEccezioneConListaErrori() {
    assertDoesNotThrow( () -> validator.creaOrdine(anOrdinePizzaDto().build()));
  }
  @Test
  void validaStatoOrdine_statoNonValido_rilasciaEccezioneConListaErrori() {
    assertThatThrownBy(
        () ->validator.validaStatoOrdine(anOrdinePizzaEntity()
                .stato(Stato.PRONTO.name())
            .build())
    ).isInstanceOf(ValidationException.class)
        .hasMessage(":Ordine gia pronto!");
    ;
  }
}