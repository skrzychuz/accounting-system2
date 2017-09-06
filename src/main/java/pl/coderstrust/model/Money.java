package pl.coderstrust.model;

import java.math.BigDecimal;

public class Money {

  private BigDecimal amount;
  private Currency currency;

  public Money(BigDecimal amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public Money(){}

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }
}
