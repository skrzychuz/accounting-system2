package pl.coderstrust.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pl.coderstrust.InvoicesGenerator;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Invoice.Builder;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public abstract class DatabaseTestAbstract {

  private InvoicesGenerator invoicesGenerator = new InvoicesGenerator();

  protected abstract Database getDatabase() throws IOException;

  @Test
  public void shouldSaveInvoiceToDatabase() throws Exception {
    // given
    Invoice invoice1 = new Builder()
        .withLocalDate(LocalDate.of(2016, 7, 15))
        .withAmount(BigDecimal.TEN)
        .withVatRate(BigDecimal.TEN)
        .withDescription("parowar")
        .build();

    Invoice invoice2 = new Builder()
        .withLocalDate(LocalDate.of(2016, 5, 15))
        .build();

    Invoice invoice3 = new Builder()
        .withLocalDate(LocalDate.of(2016, 6, 15))
        .build();

    Database database = getDatabase();
    List<Invoice> listOfInvoice = database.getInvoices();
    final int sizeBeforeAdding = listOfInvoice.size();

    // when
    database.saveInvoice(invoice1);
    database.saveInvoice(invoice2);
    database.saveInvoice(invoice3);

    // then
    assertNotNull("should not be a null", listOfInvoice);
    assertEquals(sizeBeforeAdding + 3, database.getInvoices().size());
  }

  @Test
  public void shouldGetInvoicesFromDatabase() throws Exception {
    // given
    Database database = getDatabase();
    Invoice invoice = new Builder()
        .withLocalDate(LocalDate.of(2016, 2, 15))
        .build();

    // when
    database.saveInvoice(invoice);
    List<Invoice> invoiceList = database.getInvoices();

    // then
    assertNotNull("should not be null", invoiceList);
    assertNotNull("should not be null", invoiceList.get(invoiceList.size() - 1));
  }

  @Test
  public void shouldGetInvoicesFromDayToDayIn2016() throws Exception {
    // given
    List<Invoice> randomList = invoicesGenerator.invoicesGeneratorWithRandomDateFrom2016(15);
    Database database = getDatabase();

    for (Invoice invoice1 : randomList) {
      database.saveInvoice(invoice1);
    }

    // when
    List<Invoice> invoicesFromDayToDay = database
        .getListOfInvoicesFromGivenPeriod(LocalDate.of(2016, 3, 1), LocalDate.of(2016, 6, 30));

    // then
    for (Invoice invoice : invoicesFromDayToDay) {
      assertTrue("error in" + invoice.getLocalDate().toString(),
          invoice.getLocalDate().isAfter(LocalDate.of(2016, 3, 1).minusDays(1)) && invoice
              .getLocalDate()
              .isBefore(LocalDate.of(2016, 6, 30).plusDays(1)));
    }
  }

  @Test
  public void shouldGetInvoicesFromDatabaseSortedByDate() throws Exception {
    // given
    Database database = getDatabase();
    List<Invoice> randomList = invoicesGenerator.invoicesGeneratorWithRandomDateFrom2016(15);
    for (Invoice invoice : randomList) {
      database.saveInvoice(invoice);
    }

    // when
    List<Invoice> invoiceList = database.getInvoices();

    // then
    for (int i = 0; i < invoiceList.size() - 1; i++) {
      assertTrue(
          "error in :" + i + " " + invoiceList.get(i).getLocalDate().toString() + " " + invoiceList
              .get(i + 1).getLocalDate().toString(),
          !invoiceList.get(i).getLocalDate().isAfter(invoiceList.get(i + 1).getLocalDate()));
    }
    assertNotNull("should not be null", invoiceList);
  }

  @Test
  public void shouldCheckInvoicesFromGivenPeriod() throws Exception {
    // given
    Database database = getDatabase();
    List<Invoice> invoicesInOrder = invoicesGenerator
        .invoiceGeneratorFor30DaysInJanuary2016InSuccession();

    for (Invoice invoice : invoicesInOrder) {
      database.saveInvoice(invoice);
    }

    // when
    List<Invoice> actualList = (database
        .getListOfInvoicesFromGivenPeriod(LocalDate.of(2016, 1, 15), LocalDate.of(2016, 1, 25)));

    // then
    assertTrue(!actualList.get(0).getLocalDate().isBefore(LocalDate.of(2016, 1, 15)));
    assertTrue(
        !actualList.get(actualList.size() - 1).getLocalDate().isAfter(LocalDate.of(2016, 1, 25)));
  }
}