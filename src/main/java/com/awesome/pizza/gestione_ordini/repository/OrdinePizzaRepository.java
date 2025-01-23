package com.awesome.pizza.gestione_ordini.repository;

import com.awesome.pizza.gestione_ordini.repository.model.OrdinePizzaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdinePizzaRepository extends JpaRepository<OrdinePizzaEntity,Long> {
  List<OrdinePizzaEntity> findByStato(String stato);
}
