package pl.coderstrust.model;

import pl.coderstrust.model.InvoiceBulider.BuyerBulider;
import pl.coderstrust.model.InvoiceBulider.EntryBulider;
import pl.coderstrust.model.InvoiceBulider.SellerBulider;

import java.math.BigDecimal;
import java.time.LocalDate;

public class main {


  public static void main(String[] args) {

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
            .withAmount(BigDecimal.valueOf(150))
            .withVatRate(20)
            .bulid())
        .withLocalDate(LocalDate.now())
        .build();

    System.out.println(invoice);

  }
}
