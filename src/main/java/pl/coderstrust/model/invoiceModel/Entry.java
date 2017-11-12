package pl.coderstrust.model.invoiceModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import java.math.BigDecimal;

@Entity
public class Entry {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String description;
  private int vatRate;
  private BigDecimal amount;
  private BigDecimal vatAmount; // TODO -- consider enum instead of BigDecimal
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "invoice_id")
  private Invoice invoice;

  public Entry() {
  }

  public Entry(String description, int vatRate, BigDecimal amount) {
    this.description = description;
    this.vatRate = vatRate;
    this.amount = amount;
    this.vatAmount = amount.multiply(BigDecimal.valueOf(vatRate)).divide(BigDecimal.valueOf(100));
  }
//  @JsonIgnore
//  public Invoice getInvoice() {
//    return invoice;
//  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getVatRate() {
    return vatRate;
  }

  public void setVatRate(int vatRate) {
    this.vatRate = vatRate;
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

  @Override
  public String toString() {
    return "Entry{"
        + "description='" + description
        + '\''
        + ", vatRate=" + vatRate
        + ", amount=" + amount
        + ", vatAmount=" + vatAmount
        + '}';
  }
}
