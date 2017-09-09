package pl.coderstrust.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice implements Comparable<Invoice> {

  private int id;
  private String descripction;
  private Money amount;
  private LocalDate localDate;

  /**
   * Counstructor2.+++++
   */

  public Invoice() {
  }

  /**
   * Counstructor.+++++.
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

  public void setDescripction(String descripttion) {
    this.descripction = descripction;
  }

  public Money getAmount() {
    return amount;
  }

  public void setAmount(Money amount) {
    this.amount = amount;
  }

  @Override
  public int compareTo(Invoice o) {
    return 0;
  }

  public static class Builder{
    private int id;
    private String description;

    Builder withId(int id){
      this.id = id;
      return this;
    }

    Builder withDescription(String description){
      this.description = description;
      return this;
    }

    Invoice build(){
      return new Invoice(id, description, null, null);
    }
  }

  public static void main(String[] args) {
    new Invoice.Builder()
        .withId(1)
        .withDescription("")
        .build();
  }
}
