package pl.coderstrust.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

public class Invoice implements Comparator<Invoice> {

  private int id;
  private String description;
  private BigDecimal amount;
  private BigDecimal vatRate;
  private BigDecimal vatAmount;
  private LocalDate localDate;

  /**
   * Default Constructor to initialize the object.
   */
  public Invoice() {
  }

  /**
   * Personalized Constructor to initialize the object.
   */
  private Invoice(int id, String description, BigDecimal amount, BigDecimal vatRate,
      LocalDate date) {
    this.id = id;
    this.description = description;
    this.amount = amount;
    this.vatRate = vatRate;
    this.localDate = date;
  }

  public static void main(String[] args) {
    new Invoice.Builder()
        .withId(1)
        .build();
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
  public int compare(Invoice i1, Invoice i2) {
    if (i1.getLocalDate().isBefore(i2.getLocalDate())) {
      return -1;
    } else if (i1.getLocalDate().isAfter(i2.getLocalDate())) {
      return 1;
    }
    return 0;
  }

  public static class Builder {

    private int id;
    private String description;
    private BigDecimal amount;
    private BigDecimal vatRate;
    private LocalDate localDate;

    public Builder withId(int id) {
      this.id = id;
      return this;
    }

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
      return new Invoice(id, description, amount, vatRate, localDate);
    }
  }
}

