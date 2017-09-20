package pl.coderstrust.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice.Builder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceBookTest {

  @Mock
  private Database databaseMock;


  @Test
  public void shouldReturnEmptyListIfItsEmpty() throws Exception {
    // given
    InvoiceBook invoiceBook = new InvoiceBook(databaseMock);

    // when
    List<Invoice> invoiceList = invoiceBook.getInvoices();

    // then
    assertNotNull("should not be null", invoiceList);
    assertEquals(0, invoiceList.size());
  }

  @Test
  public void shouldReturnSingleOrMoreInvoiceFromBook() throws Exception {
    // given
    InvoiceBook invoiceBook = new InvoiceBook(databaseMock);

    Invoice invoice = new Builder()
        .withLocalDate(LocalDate.of(2016, 7, 15))
        .build();
    Invoice invoice2 = new Builder()
        .withLocalDate(LocalDate.of(2016, 7, 15))
        .build();

    when(databaseMock.getInvoices()).thenReturn(Collections.singletonList(invoice));

    // when
    invoiceBook.addInvoices(invoice);
    invoiceBook.addInvoices(invoice2);
    List<Invoice> invoiceList = invoiceBook.getInvoices();

    // then
    assertNotNull("should not be null", invoiceList);
    assertEquals("List size 1!?", 1, invoiceList.size());
    assertEquals(invoice, invoiceList.get(0));
  }

}