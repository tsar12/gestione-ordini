package com.awesome.pizza.gestione_ordini.builders;

import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import com.awesome.pizza.gestione_ordini.repository.model.OrdinePizzaEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class OrdinePizzaEntityBuilder {

  private Long id = 1L;
  private String tipologia = "margherita";
  private int quantita = 1;
  private String stato = "IN_PREPARAZIONE";
  private String codiceOrdine = "123L";
  private LocalDateTime dataInserimentoOrdine = LocalDate.of(2025,1,1)
      .atStartOfDay();
  private LocalDateTime dataCompletamentoOrdine = LocalDate.of(2025,1,1)
      .atStartOfDay();;

  private OrdinePizzaEntityBuilder() {
  }

  public static OrdinePizzaEntityBuilder anOrdinePizzaEntity() {
    return new OrdinePizzaEntityBuilder();
  }

  public OrdinePizzaEntityBuilder id(Long id) {
    this.id = id;
    return this;
  }

  public OrdinePizzaEntityBuilder tipologia(String tipologia) {
    this.tipologia = tipologia;
    return this;
  }

  public OrdinePizzaEntityBuilder quantita(int quantita) {
    this.quantita = quantita;
    return this;
  }

  public OrdinePizzaEntityBuilder stato(String stato) {
    this.stato = stato;
    return this;
  }

  public OrdinePizzaEntityBuilder codiceOrdine(String codiceOrdine) {
    this.codiceOrdine = codiceOrdine;
    return this;
  }

  public OrdinePizzaEntityBuilder dataInserimentoOrdine(LocalDateTime dataInserimentoOrdine) {
    this.dataInserimentoOrdine = dataInserimentoOrdine;
    return this;
  }

  public OrdinePizzaEntityBuilder dataCompletamentoOrdine(LocalDateTime dataCompletamentoOrdine) {
    this.dataCompletamentoOrdine = dataCompletamentoOrdine;
    return this;
  }

  public OrdinePizzaEntity build() {
    OrdinePizzaEntity ordinePizzaEntity = new OrdinePizzaEntity();
    ordinePizzaEntity.setId(id);
    ordinePizzaEntity.setTipologia(tipologia);
    ordinePizzaEntity.setQuantita(quantita);
    ordinePizzaEntity.setStato(stato);
    ordinePizzaEntity.setCodiceOrdine(codiceOrdine);
    ordinePizzaEntity.setDataInserimentoOrdine(dataInserimentoOrdine);
    ordinePizzaEntity.setDataCompletamentoOrdine(dataCompletamentoOrdine);
    return ordinePizzaEntity;
  }
}
