package pl.coderstrust.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.FileProcessor;
import pl.coderstrust.database.file.IdGenerator;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.file.JsonHelper;
import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.model.Invoice.Builder;

import java.io.File;
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


  @Test
  public void shouldCheckExpectedIdNumbersWithFileDataBase() throws Exception {
    // given
    File fileForTests = new File("src\\test\\resources\\invoiceBookTest.json");
    File fileIdforTests = new File("src\\test\\resources\\idTest.json");
    String testPath = fileForTests.getPath();
    Database database = new InFileDatabase(new File(testPath), new JsonHelper(),
        new FileProcessor(), new IdGenerator(new FileProcessor()));

    InvoiceBook invoiceBook = new InvoiceBook(database);

    Invoice invoice1 = new Builder()
        .withLocalDate(LocalDate.of(2016, 4, 15))
        .build();
    Invoice invoice2 = new Builder()
        .withLocalDate(LocalDate.of(2016, 6, 15))
        .build();
    Invoice invoice3 = new Builder()
        .withLocalDate(LocalDate.of(2016, 5, 15))
        .build();

    // when
    invoiceBook.addInvoices(invoice1);
    invoiceBook.addInvoices(invoice2);
    invoiceBook.addInvoices(invoice3);
    List<Invoice> invoiceActualList = invoiceBook.getInvoices();

    // then
    assertEquals(invoiceActualList.get(invoiceActualList.size() - 1).getId(),
        invoiceActualList.get(invoiceActualList.size() - 2).getId() + 1);
  }

  @Ignore // without implementation ID Generator in InMemoryDatabase
  @Test
  public void shouldCheckExpectedIdNumbersWithMemoryDatabase() throws Exception {
    // given
    Database database = new InMemoryDatabase();
    InvoiceBook invoiceBook = new InvoiceBook(database);

    Invoice invoice1 = new Builder()
        .withLocalDate(LocalDate.of(2016, 4, 15))
        .build();
    Invoice invoice2 = new Builder()
        .withLocalDate(LocalDate.of(2016, 6, 15))
        .build();
    Invoice invoice3 = new Builder()
        .withLocalDate(LocalDate.of(2016, 5, 15))
        .build();

    // when
    invoiceBook.addInvoices(invoice1);
    invoiceBook.addInvoices(invoice2);
    invoiceBook.addInvoices(invoice3);
    List<Invoice> invoiceActualList = invoiceBook.getInvoices();

    // then
    assertEquals(
        invoiceActualList
            .get(invoiceActualList.size() - 1).getId(),
        invoiceActualList
            .get(invoiceActualList.size() - 2).getId() + 1);
  }
}