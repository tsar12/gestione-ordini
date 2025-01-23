package com.awesome.pizza.gestione_ordini.service;

import com.awesome.pizza.gestione_ordini.controller.model.dto.OrdinePizzaDto;
import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import com.awesome.pizza.gestione_ordini.repository.OrdinePizzaRepository;
import com.awesome.pizza.gestione_ordini.repository.model.OrdinePizzaEntity;
import com.awesome.pizza.gestione_ordini.service.mapper.OrdinePizzaMapper;
import com.awesome.pizza.gestione_ordini.service.validator.OdineValidator;
import com.awesome.pizza.gestione_ordini.util.DateUtils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrdineService {

  private final OrdinePizzaRepository repository;
  private final OrdinePizzaMapper mapper;
  private final OdineValidator validator;
  private final DateUtils dateUtils;

  public OrdinePizzaDto creaOrdine(OrdinePizzaDto dto) {
    validator.creaOrdine(dto);
    return mapper.map(repository.save(mapper.map(dto)));
  }

  public OrdinePizzaDto completaOrdine(Long id) {
    OrdinePizzaEntity pizzaEntity = repository.findById(id)
        .orElseThrow();
    validator.validaStatoOrdine(pizzaEntity);
    return mapper.map(repository.save(aggiornaEntita(pizzaEntity)));
  }

  private OrdinePizzaEntity aggiornaEntita(OrdinePizzaEntity ordine) {
    ordine.setDataCompletamentoOrdine(dateUtils.now());
    ordine.setStato(Stato.PRONTO.name());
    return ordine;
  }

  public List<OrdinePizzaDto> trovaTuttiOrdini() {
    return repository.findAll()
        .stream()
        .map(mapper::map)
        .collect(Collectors.toList());
  }

  public List<OrdinePizzaDto> trovaOrdini(Stato stato) {
    return repository.findByStato(stato.name())
        .stream()
        .map(mapper::map)
        .collect(Collectors.toList());
  }
}
