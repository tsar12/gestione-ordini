package com.awesome.pizza.gestione_ordini.controller;

import static com.awesome.pizza.gestione_ordini.builders.OrdinePizzaDtoBuilder.anOrdinePizzaDto;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awesome.pizza.gestione_ordini.UnitTest;
import com.awesome.pizza.gestione_ordini.controller.model.dto.OrdinePizzaDto;
import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import com.awesome.pizza.gestione_ordini.service.OrdineService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class OrdineControllerTest extends UnitTest {
  @InjectMocks
  OrdineController ordineController;
  @Mock
  OrdineService ordineService;

  @Test
  void creaOrdine_riceveRichiestaCreazioneOrdinePizza_richiamaServiceCreaOrdine() {
    OrdinePizzaDto ordinePizzaDto = anOrdinePizzaDto().build();
    when(ordineService.creaOrdine(ordinePizzaDto))
        .thenReturn(ordinePizzaDto);

    ordineController.crea(ordinePizzaDto);

    verify(ordineService).creaOrdine(ordinePizzaDto);
  }
  @Test
  void completaOrdine_riceveRichiestaDiCompletamentoOrdinePizza_richiamaServiceCompletaOrdine() {
    OrdinePizzaDto ordinePizzaDto = anOrdinePizzaDto().build();
    when(ordineService.completaOrdine(1L))
        .thenReturn(ordinePizzaDto);

    ordineController.completaOrdine(1L);

    verify(ordineService).completaOrdine(1L);
  }

  @Test
  void ordiniCompletati_riceveRichiestaOrdiniCompletati_richiamaServiceTrovaOrdini() {
    OrdinePizzaDto ordinePizzaDto = anOrdinePizzaDto().build();
    when(ordineService.trovaOrdini(Stato.PRONTO))
        .thenReturn(asList(ordinePizzaDto));

    List<OrdinePizzaDto> ordini = ordineController.ordiniCompletati();

    verify(ordineService).trovaOrdini(Stato.PRONTO);
  }

  @Test
  void inPreparazione_riceveRichiestaDiOrdiniInPrepazione_richiamaServiceTrovaOrdini() {
    OrdinePizzaDto ordinePizzaDto = anOrdinePizzaDto().build();
    when(ordineService.trovaOrdini(Stato.IN_PREPARAZIONE))
        .thenReturn(asList(ordinePizzaDto));

    List<OrdinePizzaDto> ordini = ordineController.ordiniInPreparazione();

    verify(ordineService).trovaOrdini(Stato.IN_PREPARAZIONE);
  }
  @Test
  void tuttiOrdini_riceveRichiestaDiCompletamentoOrdinePizza_richiamaServiceTuttiOrdini() {
    OrdinePizzaDto ordinePizzaDto = anOrdinePizzaDto().build();
    when(ordineService.trovaTuttiOrdini())
        .thenReturn(asList(ordinePizzaDto));

    List<OrdinePizzaDto> ordini = ordineController.tuttiOrdini();

    verify(ordineService).trovaTuttiOrdini();
  }
}