package pl.coderstrust.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;
import pl.coderstrust.InvoicesGenerator;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


public abstract class DatabaseTestAbstract {

  protected InvoicesGenerator invoicesGenerator = new InvoicesGenerator();


  protected abstract Database getDatabase();

  @Test
  public void shouldSaveInvoiceToDatabase() throws Exception {
    // given
    Invoice invoice = new Invoice(54, "yoyoyo",
        new Money(BigDecimal.valueOf(150), BigDecimal.TEN, Currency.PLN),
        LocalDate.of(2016, 5, 15));

    Database database = getDatabase();
    final List<Invoice> listOfInvoice = database.getInvoices();
    int sizeBeforeAdding = listOfInvoice.size();

    // when

    database.saveInvoice(invoice);

    // then

    assertNotNull("should not be a null", listOfInvoice);
    assertEquals(sizeBeforeAdding + 1, database.getInvoices().size());
  }


  @Test
  public void shouldGetInvoicesFromDatabase() throws Exception {

    Database database = getDatabase();
    Invoice invoice = new Invoice(58, "asdf",
        new Money(BigDecimal.valueOf(800), BigDecimal.valueOf(22), Currency.PLN),
        LocalDate.of(2017, 8, 30));

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
    List<Invoice> randomList = invoicesGenerator.randomInoviceList(150);
    Database database = getDatabase();
    for (Invoice invoice : randomList) {
      database.saveInvoice(invoice);
    }

    // when

    List<Invoice> invoicesFromDayToDay = database
        .getListOfInvoicesFromPeriodTime(LocalDate.of(2016, 3, 1), LocalDate.of(2016, 6, 30));

    //   invoicesFromDayToDay.get(5).setLocalDate(LocalDate.of(2016, 2,15));

    // then

    for (Invoice invoice : invoicesFromDayToDay) {
      assertTrue(invoice.getLocalDate().isAfter(LocalDate.of(2016, 3, 1)) && invoice.getLocalDate()
          .isBefore(LocalDate.of(2016, 6, 30)));

    }

  }
}