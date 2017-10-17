package pl.coderstrust.model.invoiceModel;

import pl.coderstrust.model.invoiceVisitorPattern.InvoiceVisitable;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceVisitor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Invoice implements InvoiceVisitable {

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
    this.amount =  this.getAmountFromEntries(entries);
    this.vatAmount = this.getVatAmountFromEntries(entries);
  }

  public BigDecimal getAmountFromEntries(List<Entry> entryList) {
    BigDecimal amountFromEntries = BigDecimal.valueOf(0);
    for (Entry e : entryList) {
      amountFromEntries = amountFromEntries.add(e.getAmount());
    }
    return amountFromEntries;
  }

  public BigDecimal getVatAmountFromEntries(List<Entry> entryList) {
    BigDecimal amountFromEntries = BigDecimal.valueOf(0);
    for (Entry e : entryList) {
      amountFromEntries = amountFromEntries.add(e.getVatAmount());
    }
    return amountFromEntries;
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

  @Override
  public BigDecimal accept(InvoiceVisitor invoiceVisitor) {
    return invoiceVisitor.visit(this);

  }
}




