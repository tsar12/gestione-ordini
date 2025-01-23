package com.awesome.pizza.gestione_ordini.service.mapper;

import com.awesome.pizza.gestione_ordini.controller.model.dto.OrdinePizzaDto;
import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import com.awesome.pizza.gestione_ordini.repository.model.OrdinePizzaEntity;
import com.awesome.pizza.gestione_ordini.util.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrdinePizzaMapper {

  private final DateUtils dateUtils;

  public OrdinePizzaEntity map(OrdinePizzaDto dto) {
    return new OrdinePizzaEntity(
        dto.getTipologia(),
        dto.getQuantita(),
        dto.getStato().name(),
        dto.getCodiceOrdine(),
        dateUtils.now()
    );
  }

  public OrdinePizzaDto map(OrdinePizzaEntity entity) {
    return new OrdinePizzaDto(
        entity.getId(),
        entity.getTipologia(),
        entity.getQuantita(),
        Stato.valueOf(entity.getStato()),
        entity.getCodiceOrdine(),
        entity.getDataInserimentoOrdine(),
        entity.getDataCompletamentoOrdine()
    );
  }
}
