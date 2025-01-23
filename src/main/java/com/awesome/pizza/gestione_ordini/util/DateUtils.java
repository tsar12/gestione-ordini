package com.awesome.pizza.gestione_ordini.util;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class DateUtils {

  public LocalDateTime now() {
    return LocalDateTime.now();
  }
}
