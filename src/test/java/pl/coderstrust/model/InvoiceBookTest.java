package pl.coderstrust.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.InvoicesGenerator;
import pl.coderstrust.database.Database;

import java.math.BigDecimal;
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
    List<Invoice> invoiceList = invoiceBook.getInvoicesIB();

    // then
    assertNotNull("should not be null", invoiceList);
    assertEquals(0, invoiceList.size());
  }

  @Test
  public void shouldReturnSingleOrMoreInvoiceFromBook() throws Exception {
    // given
    InvoiceBook invoiceBook = new InvoiceBook(databaseMock);
    Invoice invoice = new Invoice(54, "vegetables",
        new Money(BigDecimal.TEN, BigDecimal.valueOf(22), Currency.PLN),
        LocalDate.of(2015, 5, 23));
    when(databaseMock.getInvoices()).thenReturn(Collections.singletonList(invoice));

    // when
    invoiceBook.addInvoiceIB(invoice);
    List<Invoice> invoiceList = invoiceBook.getInvoicesIB();

    // then
    assertNotNull("should not be null", invoiceList);
    assertEquals("List size 1!?", 1, invoiceList.size());
    assertEquals(invoice, invoiceList.get(0));
  }

  @Test
  public void getFromToListOfInvoicesInBookTEST() throws Exception {
    // given
    InvoiceBook invoiceBook = new InvoiceBook(databaseMock);
    InvoicesGenerator invoicesGenerator = new InvoicesGenerator();
    List<Invoice> fakeList = invoicesGenerator.randomInoviceList(200);

    when(databaseMock.getListOfInvoicesFromPeriodTime(any(LocalDate.class), any(LocalDate.class))).thenReturn(fakeList);

    // when
    List<Invoice> someInvoices = invoiceBook.getFromToListOfInvoicesInBook(LocalDate.of(2016, 3, 1), LocalDate.of(2016, 6, 30));
    int i=0;
    // then
  }


}