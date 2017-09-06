package pl.coderstrust.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice {

  private int id;
  private String descripction;
  private Money amount;
  private LocalDate localDate;

  /**
   * Counstructor2.
   */

  public Invoice() {

  }

  /**
   * Counstructor.
   */

  public Invoice(int id, String descripction, Money amount, LocalDate date) {
    this.id = id;
    this.descripction = descripction;
    this.amount = amount;
    this.localDate = date;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
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

  public Money getAmount() {
    return amount;
  }

  public void setAmount(Money amount) {
    this.amount = amount;
  }
}
