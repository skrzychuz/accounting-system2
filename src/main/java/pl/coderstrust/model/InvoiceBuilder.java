package pl.coderstrust.model;

import pl.coderstrust.model.invoiceModel.Buyer;
import pl.coderstrust.model.invoiceModel.Entry;
import pl.coderstrust.model.invoiceModel.Invoice;
import pl.coderstrust.model.invoiceModel.Seller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceBuilder {

  private Seller seller;
  private Buyer buyer;
  private List<Entry> entries = new ArrayList<>();
  private LocalDate localDate;

  public InvoiceBuilder withSeller(Seller seller) {
    this.seller = seller;
    return this;
  }

  public InvoiceBuilder withBuyer(Buyer buyer) {
    this.buyer = buyer;
    return this;
  }

  public InvoiceBuilder withEntry(Entry entry) {
    this.entries.add(entry);
    return this;
  }

  public InvoiceBuilder withLocalDate(LocalDate localDate) {
    this.localDate = localDate;
    return this;

  }

  public Invoice build() {
    return new Invoice(seller, buyer, entries, localDate);
  }


  public static class SellerBulider {

    String name;
    String taxpayerIdentificationNumber;

    public SellerBulider withName(String name) {
      this.name = name;
      return this;
    }

    public SellerBulider withIdNumber(String idNumber) {
      this.taxpayerIdentificationNumber = idNumber;
      return this;
    }

    public Seller bulid() {
      return new Seller(name, taxpayerIdentificationNumber);
    }
  }

  public static class BuyerBulider {

    String name;
    String taxpayerIdentificationNumber;

    public BuyerBulider withName(String name) {
      this.name = name;
      return this;
    }

    public BuyerBulider withIdNumber(String idNumber) {
      this.taxpayerIdentificationNumber = idNumber;
      return this;
    }

    public Buyer bulid() {
      return new Buyer(name, taxpayerIdentificationNumber);
    }
  }
  public static class EntryBulider {

    private String description;
    private int vatRate;
    private BigDecimal amount;


    public EntryBulider withDescriiption(String descriiption) {
      this.description = descriiption;
      return this;
    }

    public EntryBulider withAmount(BigDecimal amount) {
      this.amount = amount;
      return this;
    }

    public EntryBulider withVatRate(int vatRate) {
      this.vatRate = vatRate;
      return this;
    }

    public Entry bulid() {
      return new Entry(description, vatRate, amount);
    }
  }

}
