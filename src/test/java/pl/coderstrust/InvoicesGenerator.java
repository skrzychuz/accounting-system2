package pl.coderstrust;

import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class InvoicesGenerator {

  public List<Invoice> randomInoviceList(int listSize) {
    List<Invoice> randomInvoices = new ArrayList<>();
    for (int i = 0; i < listSize; i++) {

      Random randomDay = new Random();
      Random randomMonth = new Random();

      int day = randomDay.nextInt(29) + 1;
      int month = randomMonth.nextInt(11) + 1;
      int year = 2016;

      Invoice invoice = new Invoice(i, "yoyoyo",
          new Money(BigDecimal.valueOf(150), BigDecimal.TEN, Currency.PLN),
          LocalDate.of(year, month, day));

      randomInvoices.add(invoice);
    }
    return randomInvoices;
  }
}
