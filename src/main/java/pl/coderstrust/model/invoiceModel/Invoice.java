package pl.coderstrust.model.invoiceModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pl.coderstrust.model.invoiceVisitorPattern.InvoiceVisitable;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceVisitor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

@Entity
@Table(name = "Invoices")
public class Invoice implements InvoiceVisitable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "invoice_id")
  private int id;
  @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
  @JoinColumn
  private Seller seller;
  @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
  @JoinColumn
  private Buyer buyer;
  @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
  private List<Entry> entries;

  private BigDecimal amount;
  private BigDecimal vatAmount;
  private LocalDate localDate;

  /**
   * Default Constructor to create the object.
   */

  public Invoice() {
  }

  /**
   * Constructor 2.
   */
  public Invoice(Seller seller, Buyer buyer, List<Entry> entries, LocalDate localDate) {
    this.seller = seller;
    this.buyer = buyer;
    this.entries = entries;
    this.localDate = localDate;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    BigDecimal amountFromEntries = BigDecimal.valueOf(0);
    for (Entry e : this.entries) {
      amountFromEntries = amountFromEntries.add(e.getAmount());
    }
    return amountFromEntries;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getVatAmount() {
    BigDecimal amountFromEntries = BigDecimal.valueOf(0);
    for (Entry e : this.entries) {
      amountFromEntries = amountFromEntries.add(e.getVatAmount());
    }
    return amountFromEntries;
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




