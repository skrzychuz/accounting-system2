package pl.coderstrust;

import pl.coderstrust.model.invoiceModel.Invoice;
import pl.coderstrust.model.InvoiceBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InvoicesGenerator {

  /**
   * Generate list Of Invoices With Random Dates In 2016.
   */
  public List<Invoice> generateListOfInvoicesWithRandomDatesIn2016(int listSize) {
    List<Invoice> randomInvoices = new ArrayList<>();

    for (int i = 0; i < listSize; i++) {
      Random randomDay = new Random();
      Random randomMonth = new Random();
      int day = randomDay.nextInt(29) + 1;
      int month = randomMonth.nextInt(11) + 1;
      int year = 2016;

      Invoice invoice = new InvoiceBuilder()
          .withLocalDate(LocalDate.of(year, month, day))
          .build();

      randomInvoices.add(invoice);
    }
    return randomInvoices;
  }

  /**
   * Genereata List Of Invoices From January 2016 With Succession Id.
   */
  public List<Invoice> genereataListOfInvoicesFromJanuary2016WithSuccessionId() {

    List<Invoice> invoicesInSuccession = new ArrayList<>();

    for (int i = 1; i <= 30; i++) {
      Invoice invoice = new InvoiceBuilder()
          .withLocalDate(LocalDate.of(2016, 1, i))
          .build();
      invoice.setId(i);
      invoicesInSuccession.add(invoice);
    }

    return invoicesInSuccession;
  }
}
