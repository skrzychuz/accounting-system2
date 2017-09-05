package pl.coderstrust.model;

import java.math.BigDecimal;

public class Invoice {

  private BigDecimal value;
  private String description;

  public Invoice () {
    this.value = BigDecimal.TEN;
    this.description = "Time Machine";
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
