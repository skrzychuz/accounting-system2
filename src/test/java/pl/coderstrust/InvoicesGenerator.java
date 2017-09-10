package pl.coderstrust;

import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Invoice.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InvoicesGenerator {

  public List<Invoice> invoicesGeneratorWithRandomDateFrom2016(int listSize) {
    List<Invoice> randomInvoices = new ArrayList<>();

    for (int i = 0; i < listSize; i++) {
      Random randomDay = new Random();
      Random randomMonth = new Random();
      int day = randomDay.nextInt(29) + 1;
      int month = randomMonth.nextInt(11) + 1;
      int year = 2016;

      Invoice invoice = new Builder()
          .withLocalDate(LocalDate.of(year,month,day))
          .build();

      randomInvoices.add(invoice);
    }
    return randomInvoices;
  }

  public Invoice invoiceGeneratorWithRandomDateFrom2016() {

    Random randomDay = new Random();
    Random randomMonth = new Random();

    int day = randomDay.nextInt(29) + 1;
    int month = randomMonth.nextInt(11) + 1;
    int year = 2016;

//    Invoice invoice = new Invoice(33, "yo", BigDecimal.valueOf(150), BigDecimal.valueOf(23),
//        LocalDate.of(year, month, day));

    return null;
  }
}
