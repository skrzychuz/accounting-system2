package pl.coderstrust.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pl.coderstrust.InvoicesGenerator;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Invoice.Builder;

import java.time.LocalDate;
import java.util.List;

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
        .withId(5)
        .withLocalDate(LocalDate.of(2016, 5, 15))
        .build();

    Invoice invoice3 = new Builder()
        .withId(5)
        .withLocalDate(LocalDate.of(2016, 6, 15))
        .build();

//    Invoice invoice1 = new Invoice(65, "yo", BigDecimal.valueOf(150), BigDecimal.valueOf(23),
//        LocalDate.of(2016, 6, 15));
//    Invoice invoice2 = new Invoice(65, "yo", BigDecimal.valueOf(150), BigDecimal.valueOf(23),
//        LocalDate.of(2016, 5, 15));
//    Invoice invoice3 = new Invoice(65, "yo", BigDecimal.valueOf(150), BigDecimal.valueOf(23),
//        LocalDate.of(2016, 7, 15));

    Database database = getDatabase();
    List<Invoice> listOfInvoice = database.getInvoices();
    int sizeBeforeAdding = listOfInvoice.size();

    // when
    database.saveInvoice(invoice1);
    database.saveInvoice(invoice2);
    database.saveInvoice(invoice3);

    List<Invoice> listOfInvoice2 = database.getInvoices();

    // then
    assertNotNull("should not be a null", listOfInvoice);
    assertEquals(sizeBeforeAdding + 3, database.getInvoices().size());
  }


  @Test
  public void shouldGetInvoicesFromDatabase() throws Exception {

    Database database = getDatabase();
    Invoice invoice = new Builder().withId(5)
        .withDescription("terefere")
        .build();
    //  Invoice invoice = new Invoice(65, "yo", BigDecimal.valueOf(150), BigDecimal.valueOf(23),LocalDate.of(2016, 6, 15));

    // when
    database.saveInvoice(invoice);
    List<Invoice> invoiceList = database.getInvoices();

    // then
    assertNotNull("should not be null", invoiceList);
    assertNotNull("should not be null", invoiceList.get(invoiceList.size() - 1));
  }


  @Test
  public void shouldSortingProvidedListByDate() throws Exception {
  }

  @Test
  public void shouldGetInvoicesFromDayToDayIn2016() throws Exception {
    // given
    List<Invoice> randomList = invoicesGenerator.invoicesGeneratorWithRandomDateFrom2016(150);
    Database database = getDatabase();
    for (Invoice invoice : randomList) {
      database.saveInvoice(invoice);
    }

    // when
    List<Invoice> invoicesFromDayToDay = database
        .getListOfInvoicesFromPeriod(LocalDate.of(2016, 3, 1), LocalDate.of(2016, 6, 30));

    // then

    for (Invoice invoice : invoicesFromDayToDay) {
      assertTrue(invoice.getLocalDate().isAfter(LocalDate.of(2016, 3, 1)) && invoice.getLocalDate()
          .isBefore(LocalDate.of(2016, 6, 30)));

    }
  }
}