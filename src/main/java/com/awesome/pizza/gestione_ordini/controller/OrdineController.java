package com.awesome.pizza.gestione_ordini.controller;

import com.awesome.pizza.gestione_ordini.controller.model.dto.OrdinePizzaDto;
import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import com.awesome.pizza.gestione_ordini.service.OrdineService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("ordini")
@AllArgsConstructor
public class OrdineController {
  private final OrdineService ordineService;

  @PostMapping("/crea")
  public OrdinePizzaDto crea(@RequestBody OrdinePizzaDto dto) {
    return ordineService.creaOrdine(dto);
  }

  @GetMapping("/completa/{id}")
  public OrdinePizzaDto completaOrdine(@PathVariable("id") Long id) {
    return ordineService.completaOrdine(id);
  }
  @GetMapping("/completati")
  public List<OrdinePizzaDto> ordiniCompletati() {
    return ordineService.trovaOrdini(Stato.PRONTO);
  }
  @GetMapping("/in-preparazione")
  public List<OrdinePizzaDto> ordiniInPreparazione() {
    return ordineService.trovaOrdini(Stato.IN_PREPARAZIONE);
  }
  @GetMapping("/tutti")
  public List<OrdinePizzaDto> tuttiOrdini() {
    return ordineService.trovaTuttiOrdini();
  }
}
