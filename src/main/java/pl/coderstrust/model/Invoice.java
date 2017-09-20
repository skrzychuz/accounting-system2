package pl.coderstrust.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice implements Comparable<Invoice> {

  private int id;
  private String description;
  private BigDecimal amount;
  private BigDecimal vatRate;
  private BigDecimal vatAmount;
  private LocalDate localDate;

  /**
   * Default Constructor to create the object.
   */
  public Invoice() {
  }

  /**
   * Personalized Constructor to create the object.
   */
  private Invoice(String description, BigDecimal amount, BigDecimal vatRate,
      LocalDate date) {

    this.description = description;
    this.amount = amount;
    this.vatRate = vatRate;
    this.localDate = date;
  }

  public BigDecimal getVatAmount(BigDecimal vatRate) {
    return this.amount.multiply(vatRate).divide(new BigDecimal(100));
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getVatAmount() {
    return vatAmount;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  @Override
  public int compareTo(Invoice invoice) {
    if (this.getLocalDate().isBefore(invoice.getLocalDate())) {
      return -1;
    } else if (this.getLocalDate().isAfter(invoice.getLocalDate())) {
      return 1;
    }
    return 0;
  }

  public static class Builder {

    private String description;
    private BigDecimal amount;
    private BigDecimal vatRate;
    private LocalDate localDate;

    public Builder withDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder withAmount(BigDecimal amount) {
      this.amount = amount;
      return this;
    }

    public Builder withVatRate(BigDecimal vatRate) {
      this.vatRate = vatRate;
      return this;
    }

    public Builder withLocalDate(LocalDate localDate) {
      this.localDate = localDate;
      return this;
    }

    public Invoice build() {
      return new Invoice(description, amount, vatRate, localDate);
    }
  }
}

