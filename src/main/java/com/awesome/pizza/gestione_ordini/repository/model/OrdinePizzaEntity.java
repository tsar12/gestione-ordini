package com.awesome.pizza.gestione_ordini.repository.model;

import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrdinePizza")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdinePizzaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @Column(name = "tipologia",nullable = false)
  private String tipologia;
  @Column(name = "quantita",nullable = false)
  private int quantita;
  @Column(name = "stato",nullable = false)
  private String stato;
  @Column(name = "codiceOrdine",nullable = false)
  private String codiceOrdine;
  @Column(name = "dataInserimentoOrdine",nullable = false)
  private LocalDateTime dataInserimentoOrdine;
  @Column(name = "dataCompletamentoOrdine")
  private LocalDateTime dataCompletamentoOrdine;

  public OrdinePizzaEntity(String tipologia, int quantita, String stato, String codiceOrdine,
      LocalDateTime dataInserimentoOrdine) {
    this.tipologia = tipologia;
    this.quantita = quantita;
    this.stato = stato;
    this.codiceOrdine = codiceOrdine;
    this.dataInserimentoOrdine = dataInserimentoOrdine;
  }
}
