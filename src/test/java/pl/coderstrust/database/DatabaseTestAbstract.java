package pl.coderstrust.database;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceBook;

import java.util.Collections;
import java.util.List;

public abstract class  DatabaseTestAbstract {

  protected abstract Database getDatabase();

  @Test
  public void shouldSaveInvoiceToDatabase() throws Exception {
    // given
    Invoice invoice = new Invoice();
    Database database = getDatabase();

    // when
    database.saveInvoice(invoice);

    // then
    final List<Invoice> listOfInvoice = database.getInvoices();

    assertNotNull("should not be a null", listOfInvoice);
    assertEquals(1, listOfInvoice.size());

  }

  @Test
  public void shouldGetInvoicesFromDatabase() throws Exception {

    Database database = getDatabase();
    Invoice invoice = new Invoice();

    // when
    database.saveInvoice(invoice);
    List<Invoice> invoiceList = database.getInvoices();

    // then
    assertNotNull("should not be null", invoiceList);
    assertEquals("List size 1!?",1, invoiceList.size());
    assertEquals(invoice.getDescription(), invoiceList.get(0).getDescription());



  }

}