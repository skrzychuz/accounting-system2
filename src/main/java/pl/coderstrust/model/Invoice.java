package pl.coderstrust.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice {

  private int id;
  private String descripction;
  private Money amount;
  private LocalDate date;


  /**
   * Counstructor.
   */
  public Invoice(int id, String descripction, Money amount) {
    this.id = id;
    this.descripction = descripction;
    this.amount = amount;
    this.date = LocalDate.now();
  }

  /**
   * Counstructor2.
   */

  public Invoice(int id, String descripction, Money amount, LocalDate date) {
    this.id = id;
    this.descripction = descripction;
    this.amount = amount;
    this.date = date;
  }

  // Invoice invoice = new Invoice
  // (75,"desc", new Money(BigDecimal.TEN, Currency.PLN), LocalDate.of(2015,2,15));

  /**
   * Counstructor.
   */

  public Invoice() {
    this.id = 5;
    this.descripction = "Time Machine";
    this.amount = new Money(BigDecimal.TEN, Currency.PLN);
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
