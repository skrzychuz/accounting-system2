package pl.coderstrust.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.database.Database;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceBookTest {

  @Mock
  Database databaseMock;


  @Test
  public void shouldReturnEmptyListIfItsEmpty() {
    // given
    InvoiceBook invoiceBook = new InvoiceBook(databaseMock);

    // when
    List<Invoice> invoiceList = invoiceBook.getInvoices();

    // then
    assertNotNull("should not be null", invoiceList);
    assertEquals(0, invoiceList.size());
  }

  @Test
  public void shouldReturnSingleOrMoreInvoiceFromBook() {
    // given
    InvoiceBook invoiceBook = new InvoiceBook(databaseMock);
    Invoice invoice = new Invoice();
    when(databaseMock.getInvoices()).thenReturn(Collections.singletonList(invoice));

    // when
    invoiceBook.addInvoice(invoice);
    List<Invoice> invoiceList = invoiceBook.getInvoices();

    // then
    assertNotNull("should not be null", invoiceList);
    assertEquals("List size 1!?",1, invoiceList.size());
    assertEquals(invoice, invoiceList.get(0));
  }
}