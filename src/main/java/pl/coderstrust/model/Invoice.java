package pl.coderstrust.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Invoice {

  private Seller seller;
  private Buyer buyer;
  private List<Entry> entries;
  private int id;
  private BigDecimal amount;
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
  public Invoice(Seller seller, Buyer buyer, List<Entry> entries, LocalDate localDate) {
    this.seller = seller;
    this.buyer = buyer;
    this.entries = entries;
    this.localDate = localDate;

    for (Entry e : entries) {
      this.amount = e.getAmount();
      this.vatAmount = e.getVatAmount();
    }
    for (Entry e : entries) {
      this.amount = e.getAmount();
    }

  }

  public Seller getSeller() {
    return seller;
  }

  public void setSeller(Seller seller) {
    this.seller = seller;
  }

  public Buyer getBuyer() {
    return buyer;
  }

  public void setBuyer(Buyer buyer) {
    this.buyer = buyer;
  }

  public List<Entry> getEntries() {
    return entries;
  }

  public void setEntries(List<Entry> entries) {
    this.entries = entries;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public void setVatAmount(BigDecimal vatAmount) {
    this.vatAmount = vatAmount;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  @Override
  public String toString() {
    return "Invoice{"
        + "seller=" + seller
        + ", buyer=" + buyer
        + ", entries=" + entries
        + ", id=" + id
        + ", amount=" + amount
        + ", vatAmount=" + vatAmount
        + ", localDate=" + localDate
        + '}';
  }
}




