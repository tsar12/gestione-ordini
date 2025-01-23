package com.awesome.pizza.gestione_ordini.service.validator;

import com.awesome.pizza.gestione_ordini.controller.model.dto.OrdinePizzaDto;
import com.awesome.pizza.gestione_ordini.controller.model.dto.Stato;
import com.awesome.pizza.gestione_ordini.repository.model.OrdinePizzaEntity;
import com.awesome.pizza.gestione_ordini.service.validator.exception.ErrorMessage;
import com.awesome.pizza.gestione_ordini.service.validator.exception.ValidationException;
import io.micrometer.common.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class OdineValidator {
  public void creaOrdine(OrdinePizzaDto dto) {
    List<ErrorMessage> errorMessages = new ArrayList<>();
    if(Objects.isNull(dto.getStato())){
      errorMessages.add(new ErrorMessage("stato","Stato non valido"));
    }
    if(StringUtils.isBlank(dto.getTipologia())){
      errorMessages.add(new ErrorMessage("tipologia","Tipologia ordine non valida"));
    }
    if(StringUtils.isBlank(dto.getCodiceOrdine())){
      errorMessages.add(new ErrorMessage("codice_ordine","Codice ordine non valido"));
    }
    if(dto.getQuantita() < 1){
      errorMessages.add(new ErrorMessage("quantita","Quantita deve essere maggiore di 0"));
    }
    lanciaEccezione(errorMessages);
  }

  private void lanciaEccezione(List<ErrorMessage> errorMessages) {
    if (!errorMessages.isEmpty()){
      throw new ValidationException(errorMessages);
    }
  }

  public void validaStatoOrdine(OrdinePizzaEntity entity){
    List<ErrorMessage> errorMessages = new ArrayList<>();
    if(Stato.PRONTO.name().equals(entity.getStato())){
      errorMessages.add(new ErrorMessage("","Ordine gia pronto!"));
    }
    lanciaEccezione(errorMessages);
  }
}
