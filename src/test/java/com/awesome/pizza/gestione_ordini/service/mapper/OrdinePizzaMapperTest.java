package com.awesome.pizza.gestione_ordini.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.awesome.pizza.gestione_ordini.UnitTest;
import com.awesome.pizza.gestione_ordini.builders.OrdinePizzaDtoBuilder;
import com.awesome.pizza.gestione_ordini.builders.OrdinePizzaEntityBuilder;
import com.awesome.pizza.gestione_ordini.controller.model.dto.OrdinePizzaDto;
import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import com.awesome.pizza.gestione_ordini.repository.model.OrdinePizzaEntity;
import com.awesome.pizza.gestione_ordini.util.DateUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class OrdinePizzaMapperTest extends UnitTest {

  private static final LocalDateTime LOCAL_DATE_TIME =
      LocalDateTime.of(LocalDate.of(2025, 1, 1),LocalTime.of(0, 0));
  @InjectMocks
  OrdinePizzaMapper ordinePizzaMapper;
  @Mock
  DateUtils dateUtils;

  @Test
  void map_OrdinePizzaDto_ritornaOrdinePizzaEntity() {
    when(dateUtils.now()).thenReturn(LOCAL_DATE_TIME);

    OrdinePizzaDto dto = OrdinePizzaDtoBuilder.anOrdinePizzaDto().build();

    OrdinePizzaEntity entity = ordinePizzaMapper.map(dto);

    assertThat(entity.getCodiceOrdine()).isEqualTo(dto.getCodiceOrdine());
    assertThat(entity.getQuantita()).isEqualTo(dto.getQuantita());
    assertThat(entity.getDataInserimentoOrdine()).isEqualTo(LOCAL_DATE_TIME);
    assertThat(entity.getTipologia()).isEqualTo(dto.getTipologia());
    assertThat(entity.getStato()).isEqualTo(dto.getStato().name());
  }

  @Test
  void map_OrdinePizzaEntity_ritornaOrdinePizzaDto() {
    OrdinePizzaEntity entity = OrdinePizzaEntityBuilder.anOrdinePizzaEntity().build();

    OrdinePizzaDto dto = ordinePizzaMapper.map(entity);

    assertThat(dto.getCodiceOrdine()).isEqualTo(entity.getCodiceOrdine());
    assertThat(dto.getQuantita()).isEqualTo(entity.getQuantita());
    assertThat(dto.getDataInserimentoOrdine()).isEqualTo(LOCAL_DATE_TIME);
    assertThat(dto.getTipologia()).isEqualTo(entity.getTipologia());
    assertThat(dto.getStato()).isEqualTo(Stato.IN_PREPARAZIONE);
  }
}