package pl.coderstrust.database;

import static org.junit.Assert.*;

import org.junit.Test;
import pl.coderstrust.model.Invoice;

import java.util.List;

public abstract class DatabaseTestAbstract {

  protected abstract Database provideDatabase();

  @Test
  public void saveInvoice() throws Exception {
  }

  @Test
  public void shouldReadInvoiceFromDatabase() throws Exception {
    // given
    Invoice invoice = new Invoice();
    Database database = provideDatabase();

    // when
    database.saveInvoice(invoice);

    // then
    final List<Invoice> listOfInvoice = database.getListOfInvoice();

    assertNotNull("should not be a null", listOfInvoice);
    assertEquals(1, listOfInvoice.size());
    assertEquals(invoice, listOfInvoice.get(0));

  }

}