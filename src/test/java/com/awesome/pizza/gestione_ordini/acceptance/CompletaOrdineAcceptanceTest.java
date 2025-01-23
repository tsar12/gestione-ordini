package com.awesome.pizza.gestione_ordini.acceptance;

import static com.awesome.pizza.gestione_ordini.builders.OrdinePizzaDtoBuilder.anOrdinePizzaDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.awesome.pizza.gestione_ordini.AcceptanceTest;
import com.awesome.pizza.gestione_ordini.controller.model.dto.OrdinePizzaDto;
import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import com.awesome.pizza.gestione_ordini.util.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

public class CompletaOrdineAcceptanceTest extends AcceptanceTest {

  @Autowired
  ObjectMapper objectMapper;
  @MockitoBean
  DateUtils dateUtils;
  private static final LocalDateTime LOCAL_DATE_TIME =
      LocalDateTime.of(LocalDate.of(2025, 1, 1), LocalTime.of(0, 0));

  @BeforeEach
  void setUpTest() {
    when(dateUtils.now()).thenReturn(LOCAL_DATE_TIME);
  }

  @Test
  void completaOrdine_riceveChiamataCompletamento_ritornaOrdineCompletato() throws Exception {
    Long idOrdine = creaOrdine();

    String ordine = get("/completa/"+idOrdine.toString())
        .andReturn().getResponse().getContentAsString();
    OrdinePizzaDto actual = objectMapper.readValue(ordine, OrdinePizzaDto.class);

    assertThat(actual.getId()).isEqualTo(1);
    assertThat(actual.getStato()).isEqualTo(Stato.PRONTO);
    assertThat(actual.getDataCompletamentoOrdine()).isEqualTo(LOCAL_DATE_TIME);
  }

  public Long creaOrdine() throws Exception {
    String ordine = post("/crea", anOrdinePizzaDto()
        .build())
        .andReturn().getResponse().getContentAsString();
    OrdinePizzaDto actual = objectMapper.readValue(ordine, OrdinePizzaDto.class);
    return actual.getId();
  }

}
