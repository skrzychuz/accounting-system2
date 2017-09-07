package pl.coderstrust.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public abstract class DatabaseTestAbstract {

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
}