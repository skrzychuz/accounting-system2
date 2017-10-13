package pl.coderstrust.model;

import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.model.InvoiceBulider.BuyerBulider;
import pl.coderstrust.model.InvoiceBulider.EntryBulider;
import pl.coderstrust.model.InvoiceBulider.SellerBulider;
import pl.coderstrust.model.invoiceModel.Invoice;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceAmount;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceTax;

import java.math.BigDecimal;
import java.time.LocalDate;

public class main {


  public static void main(String[] args) throws Exception {

    InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
    InvoiceBook invoiceBook = new InvoiceBook(inMemoryDatabase);
    InvoiceAmount invoiceAmount = new InvoiceAmount();
    InvoiceTax invoiceTax = new InvoiceTax();

    Invoice invoice = new InvoiceBulider()
        .withSeller(new SellerBulider()
            .withName("sellername")
            .withIdNumber("12345")
            .bulid())
        .withBuyer(new BuyerBulider()
            .withName("buyer")
            .withIdNumber("666")
            .bulid())
        .withEntry(new EntryBulider()
            .withDescriiption("zielony groszek")
            .withAmount(BigDecimal.TEN)
            .withVatRate(20)
            .bulid())
        .withEntry(new EntryBulider()
            .withDescriiption("orzechy wloskie")
            .withAmount(BigDecimal.valueOf(90))
            .withVatRate(20)
            .bulid())
        .withLocalDate(LocalDate.now())
        .build();

    Invoice invoice2 = new InvoiceBulider()
        .withSeller(new SellerBulider()
            .withName("sellername")
            .withIdNumber("12345")
            .bulid())
        .withBuyer(new BuyerBulider()
            .withName("buyer")
            .withIdNumber("666")
            .bulid())
        .withEntry(new EntryBulider()
            .withDescriiption("zielony groszek2")
            .withAmount(BigDecimal.TEN)
            .withVatRate(20)
            .bulid())
        .withEntry(new EntryBulider()
            .withDescriiption("orzechy wloskie2")
            .withAmount(BigDecimal.valueOf(90))
            .withVatRate(20)
            .bulid())
        .withLocalDate(LocalDate.now())
        .build();

    invoiceBook.addInvoices(invoice);
    invoiceBook.addInvoices(invoice2);

    BigDecimal big = invoiceBook.accept(invoiceAmount);
    BigDecimal big2 = invoiceBook.accept(invoiceTax);
    System.out.println(big);
    System.out.println(big2);

  }
}
