package pl.coderstrust.database;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pl.coderstrust.InvoicesGenerator;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Invoice.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class DatabaseTestAbstract {

  protected InvoicesGenerator invoicesGenerator = new InvoicesGenerator();

  protected abstract Database getDatabase();

  @Test
  public void shouldSaveInvoiceToDatabase() throws Exception {
    // given
    Invoice invoice1 = new Builder()
        .withId(5)
        .withLocalDate(LocalDate.of(2016, 7, 15))
        .build();

    Invoice invoice2 = new Builder()
        .withId(6)
        .withLocalDate(LocalDate.of(2016, 5, 15))
        .build();

    Invoice invoice3 = new Builder()
        .withId(7)
        .withLocalDate(LocalDate.of(2016, 6, 15))
        .build();

    Database database = getDatabase();
    List<Invoice> listOfInvoice = database.getInvoices();
    int sizeBeforeAdding = listOfInvoice.size();

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

    Database database = getDatabase();
    Invoice invoice = new Builder()
        .withId(5)
        .withDescription("terefere")
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
    List<Invoice> randomList = invoicesGenerator.invoicesGeneratorWithRandomDateFrom2016(150);
    Database database = getDatabase();

    for (Invoice invoice1 : randomList) {
      database.saveInvoice(invoice1);
    }

    // when
    List<Invoice> invoicesFromDayToDay = database
        .getListOfInvoicesFromGivenPeriod(LocalDate.of(2016, 3, 1), LocalDate.of(2016, 6, 30));

    // then

    for (Invoice invoice : invoicesFromDayToDay) {
      assertTrue("error?" + invoice.getLocalDate().toString(),
          invoice.getLocalDate().isAfter(LocalDate.of(2016, 3, 1).minusDays(1)) && invoice
              .getLocalDate()
              .isBefore(LocalDate.of(2016, 6, 30).plusDays(1)));

    }
  }

  @Test
  public void shouldGetInvoicesFromDatabaseSortedByDate() throws Exception {

    Database database = getDatabase();
    List<Invoice> randomList = invoicesGenerator.invoicesGeneratorWithRandomDateFrom2016(150);
    for (Invoice invoice : randomList) {
      database.saveInvoice(invoice);
    }

    // when

    List<Invoice> invoiceList = database.getInvoices();
//    // to fail test
//    Invoice invoiceHelper = invoiceList.get(50);
//    invoiceList.set(55, invoiceHelper);

    // then
    for (int i = 0; i < invoiceList.size() - 1; i++) {
      assertTrue(!invoiceList.get(i).getLocalDate().isAfter(invoiceList.get(i + 1).getLocalDate()));
    }
    assertNotNull("should not be null", invoiceList);

  }

  @Test
  public void shouldCheckInvoicesFromGivenPeriod() throws Exception {
    // given
    Database database = getDatabase();
    List<Invoice> invoicesInOrder = invoicesGenerator
        .invoiceGeneratorFor30DaysInJanuary2016InSuccession();
    List<Invoice> expectedList = invoicesGenerator
        .invoiceGeneratorFor11DaysInJanuary2016InSuccession();
    for (Invoice invoice : invoicesInOrder) {   // problem with lambda ::
      database.saveInvoice(invoice);
    }




    // when
    List<Invoice> actualList = (database
        .getListOfInvoicesFromGivenPeriod(LocalDate.of(2016, 1, 15), LocalDate.of(2016, 1, 25)));

    // then
    assertEquals(expectedList.size(), actualList.size());
    assertTrue(!actualList.get(0).getLocalDate().isBefore(LocalDate.of(2016, 1, 15)));
    assertTrue(
        !actualList.get(actualList.size() - 1).getLocalDate().isAfter(LocalDate.of(2016, 1, 25)));


  }

}