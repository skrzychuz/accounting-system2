package pl.coderstrust.model;

import java.math.BigDecimal;

public class Invoice {

  private BigDecimal value;
  private String description;

  public Invoice () {
    this.value = BigDecimal.TEN;
    this.description = "Time Machine";
  }


}
