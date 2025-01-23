package com.awesome.pizza.gestione_ordini.service;

import static com.awesome.pizza.gestione_ordini.builders.OrdinePizzaDtoBuilder.anOrdinePizzaDto;
import static com.awesome.pizza.gestione_ordini.builders.OrdinePizzaEntityBuilder.anOrdinePizzaEntity;
import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awesome.pizza.gestione_ordini.UnitTest;
import com.awesome.pizza.gestione_ordini.controller.model.dto.OrdinePizzaDto;
import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import com.awesome.pizza.gestione_ordini.repository.OrdinePizzaRepository;
import com.awesome.pizza.gestione_ordini.repository.model.OrdinePizzaEntity;
import com.awesome.pizza.gestione_ordini.service.mapper.OrdinePizzaMapper;
import com.awesome.pizza.gestione_ordini.service.validator.OdineValidator;
import com.awesome.pizza.gestione_ordini.util.DateUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class OrdineServiceTest extends UnitTest {
  @InjectMocks
  private OrdineService ordineService;
  @Mock
  private OdineValidator odineValidator;
  @Mock
  private OrdinePizzaMapper mapper;;
  @Mock
  private OrdinePizzaRepository repository;
  @Mock
  private DateUtils dateUtils;

  private static final LocalDateTime LOCAL_DATE_TIME =
      LocalDateTime.of(LocalDate.of(2025, 1, 1), LocalTime.of(0, 0));

  @Test
  void creaOrdine_richiamatoCreaOrdine_chiamaCorrettamenteLaRepoEValidator() {
    OrdinePizzaDto ordinePizzaDto = anOrdinePizzaDto().build();
    OrdinePizzaEntity ordinePizzaEntity = anOrdinePizzaEntity().build();
    when(mapper.map(ordinePizzaDto)).thenReturn(ordinePizzaEntity);
    when(mapper.map(ordinePizzaEntity)).thenReturn(ordinePizzaDto);
    when(repository.save(ordinePizzaEntity)).thenReturn(ordinePizzaEntity);

    OrdinePizzaDto actual = ordineService.creaOrdine(ordinePizzaDto);

    assertThat(actual).isEqualTo(ordinePizzaDto);
  }

  @Test
  void completaOrdine_richiamatoCompletaOrdine_chiamaCorrettamenteLaRepoEValidator() {
    OrdinePizzaDto ordinePizzaDto = anOrdinePizzaDto().build();
    OrdinePizzaEntity ordinePizzaEntity = anOrdinePizzaEntity().build();
    when(mapper.map(ordinePizzaEntity)).thenReturn(ordinePizzaDto);
    when(repository.findById(ordinePizzaEntity.getId())).thenReturn(of(ordinePizzaEntity));
    when(repository.save(ordinePizzaEntity)).thenReturn(ordinePizzaEntity);
    when(dateUtils.now()).thenReturn(LOCAL_DATE_TIME);

    OrdinePizzaDto actual = ordineService.completaOrdine(ordinePizzaDto.getId());

    assertThat(actual).isEqualTo(ordinePizzaDto);
  }

  @Test
  void trovaOrdini_conStatoInPreparazione_ritornaListaOrdini() {
    OrdinePizzaDto ordinePizzaDto = anOrdinePizzaDto().build();
    OrdinePizzaEntity ordinePizzaEntity = anOrdinePizzaEntity().build();
    when(mapper.map(ordinePizzaEntity)).thenReturn(ordinePizzaDto);
    when(repository.findByStato(ordinePizzaEntity.getStato()))
        .thenReturn(asList(ordinePizzaEntity));

    List<OrdinePizzaDto> ordini = ordineService.trovaOrdini(ordinePizzaDto.getStato());

    assertThat(ordini).isNotEmpty();
  }


  @Test
  void trovaTuttiOrdini_ordiniPresentiADb_ritornaListaOrdini() {
    OrdinePizzaDto ordinePizzaDto = anOrdinePizzaDto().build();
    OrdinePizzaEntity ordinePizzaEntity = anOrdinePizzaEntity().build();
    when(mapper.map(ordinePizzaEntity)).thenReturn(ordinePizzaDto);
    when(repository.findAll())
        .thenReturn(asList(ordinePizzaEntity));

    List<OrdinePizzaDto> ordini = ordineService.trovaTuttiOrdini();

    assertThat(ordini).isNotEmpty();
  }


  @Test
  void completaOrdine_ordineNonPresente_rilasciaEccezione() {
    OrdinePizzaEntity ordinePizzaEntity = anOrdinePizzaEntity().build();
    when(repository.findById(ordinePizzaEntity.getId())).thenReturn(empty());

    assertThatThrownBy(
        () -> ordineService.completaOrdine(ordinePizzaEntity.getId()))
        .isInstanceOf(NoSuchElementException.class);
  }
}