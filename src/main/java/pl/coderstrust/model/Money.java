package pl.coderstrust.model;

import java.math.BigDecimal;

public class Money {

  private BigDecimal amount;
  private BigDecimal vatRate;
  private BigDecimal vatAmount;
  private Currency currency;

  public Money(BigDecimal amount, BigDecimal vatRate, Currency currency) {
    this.amount = amount;
    this.currency = currency;
    this.vatAmount = amount.multiply(vatRate).divide(new BigDecimal(100));
    this.vatRate = vatRate;
  }

  public Money() {
  }

  public BigDecimal getVatRate() {
    return vatRate;
  }

  public void setVatRate(BigDecimal vatRate) {
    this.vatRate = vatRate;
  }

  public BigDecimal getVatAmount() {
    return vatAmount;
  }

  public void setVatAmount(BigDecimal vatAmount) {
    this.vatAmount = vatAmount;
  }


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
