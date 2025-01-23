package com.awesome.pizza.gestione_ordini.controller.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdinePizzaDto {

  private Long id;
  private String tipologia;
  private int quantita;
  private Stato stato;
  private String codiceOrdine;
  private LocalDateTime dataInserimentoOrdine;
  private LocalDateTime dataCompletamentoOrdine;
}
