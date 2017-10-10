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

  public BigDecimal getVatRate() {
    return vatRate;
  }

  public void setVatRate(BigDecimal vatRate) {
    this.vatRate = vatRate;
  }

  public void setVatAmount(BigDecimal vatAmount) {
    this.vatAmount = vatAmount;
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

  public BigDecimal getVatAmount2(BigDecimal vatRate) {
    return this.amount.multiply(vatRate).divide(BigDecimal.valueOf(100), 0);
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

  @Override
  public String toString() {
    return "Invoice{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", amount=" + amount +
        ", vatRate=" + vatRate +
        ", vatAmount=" + vatAmount +
        ", localDate=" + localDate +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Invoice invoice = (Invoice) o;

    if (id != invoice.id) {
      return false;
    }
    if (description != null ? !description.equals(invoice.description)
        : invoice.description != null) {
      return false;
    }
    if (amount != null ? !amount.equals(invoice.amount) : invoice.amount != null) {
      return false;
    }
    if (vatRate != null ? !vatRate.equals(invoice.vatRate) : invoice.vatRate != null) {
      return false;
    }
    if (vatAmount != null ? !vatAmount.equals(invoice.vatAmount) : invoice.vatAmount != null) {
      return false;
    }
    return localDate != null ? localDate.equals(invoice.localDate) : invoice.localDate == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (amount != null ? amount.hashCode() : 0);
    result = 31 * result + (vatRate != null ? vatRate.hashCode() : 0);
    result = 31 * result + (vatAmount != null ? vatAmount.hashCode() : 0);
    result = 31 * result + (localDate != null ? localDate.hashCode() : 0);
    return result;
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

