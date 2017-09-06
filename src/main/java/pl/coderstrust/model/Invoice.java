package pl.coderstrust.model;

import java.math.BigDecimal;

public class Invoice {
    private int id;
    private String descripction;
    private BigDecimal amount = new Money (BigDecimal.ZERO, Currency);

    public Invoice(int id, String descripction, BigDecimal amount) {
        this.id = id;
        this.descripction = descripction;
        this.amount = amount;
    }
      public Invoice() {
    this.id = 5;
    this.descripction = "Time Machine";
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripction() {
        return descripction;
    }

    public void setDescripction(String descripction) {
        this.descripction = descripction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

  public Invoice() {
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
