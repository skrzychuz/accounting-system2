package pl.coderstrust.model.invoiceModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.math.BigDecimal;
@Entity
public class Entry {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @ManyToOne
  @JoinColumn(name="invoice_id")
  Invoice invoice;

  private String description;
  private int vatRate;
  private BigDecimal amount;
  private BigDecimal vatAmount;

  public Entry() {
  }

  public Entry(String description, int vatRate, BigDecimal amount) {
    this.description = description;
    this.vatRate = vatRate;
    this.amount = amount;
    this.vatAmount = amount.multiply(BigDecimal.valueOf(vatRate)).divide(BigDecimal.valueOf(100));
  }
  public Invoice getInvoice() {
    return invoice;
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
