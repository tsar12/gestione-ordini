package com.awesome.pizza.gestione_ordini.builders;

import com.awesome.pizza.gestione_ordini.controller.model.dto.OrdinePizzaDto;
import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class OrdinePizzaDtoBuilder {

  private Long id = 1L;
  private String tipologia = "margherita";
  private int quantita = 1;
  private Stato stato = Stato.IN_PREPARAZIONE;
  private String codiceOrdine = "123L";
  private LocalDateTime dataInserimentoOrdine = LocalDate.of(2025,1,1)
      .atStartOfDay();
  private LocalDateTime dataCompletamentoOrdine = LocalDate.of(2025,1,10)
      .atStartOfDay();

  private OrdinePizzaDtoBuilder() {
  }

  public static OrdinePizzaDtoBuilder anOrdinePizzaDto() {
    return new OrdinePizzaDtoBuilder();
  }

  public OrdinePizzaDtoBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public OrdinePizzaDtoBuilder tipologia(String tipologia) {
    this.tipologia = tipologia;
    return this;
  }

  public OrdinePizzaDtoBuilder quantita(int quantita) {
    this.quantita = quantita;
    return this;
  }

  public OrdinePizzaDtoBuilder stato(Stato stato) {
    this.stato = stato;
    return this;
  }

  public OrdinePizzaDtoBuilder codiceOrdine(String codiceOrdine) {
    this.codiceOrdine = codiceOrdine;
    return this;
  }

  public OrdinePizzaDtoBuilder dataInserimentoOrdine(LocalDateTime dataInserimentoOrdine) {
    this.dataInserimentoOrdine = dataInserimentoOrdine;
    return this;
  }

  public OrdinePizzaDtoBuilder dataCompletamentoOrdine(LocalDateTime dataCompletamentoOrdine) {
    this.dataCompletamentoOrdine = dataCompletamentoOrdine;
    return this;
  }

  public OrdinePizzaDto build() {
    OrdinePizzaDto ordinePizzaDto = new OrdinePizzaDto();
    ordinePizzaDto.setId(id);
    ordinePizzaDto.setTipologia(tipologia);
    ordinePizzaDto.setQuantita(quantita);
    ordinePizzaDto.setStato(stato);
    ordinePizzaDto.setCodiceOrdine(codiceOrdine);
    ordinePizzaDto.setDataInserimentoOrdine(dataInserimentoOrdine);
    ordinePizzaDto.setDataCompletamentoOrdine(dataCompletamentoOrdine);
    return ordinePizzaDto;
  }
}
