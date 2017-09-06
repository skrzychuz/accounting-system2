package pl.coderstrust.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Money;

import java.math.BigDecimal;
import java.util.List;

public abstract class DatabaseTestAbstract {

  protected abstract Database getDatabase();

  @Test
  public void shouldSaveInvoiceToDatabase() throws Exception {
    // given
    Invoice invoice = new Invoice();
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
    Invoice invoice = new Invoice(54,"yoyoyo",new Money(BigDecimal.valueOf(150), Currency.PLN));
    invoice.setDescripction("terefere");

    // when
    database.saveInvoice(invoice);
    List<Invoice> invoiceList = database.getInvoices();

    // then
    assertNotNull("should not be null", invoiceList);
    assertTrue(invoiceList.size() > 0);
    assertEquals("terefere", invoiceList.get(invoiceList.size() - 1).getDescripction());
  }
}