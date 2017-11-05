package pl.coderstrust.database.file;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.coderstrust.InvoicesGenerator;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.DatabaseTestAbstract;
import pl.coderstrust.model.invoiceModel.Invoice;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InFileDatabaseTest extends DatabaseTestAbstract {

  @Autowired
  JsonHelper jsonHelper;
  private FileProcessor fileProcessorMock = mock(FileProcessor.class);
  private JsonHelper jsonHelperMock = mock(JsonHelper.class);
  private InvoicesGenerator invoicesGenerator = new InvoicesGenerator();
  private String testDataBase = "src\\test\\resources\\dataForTest.json";

  @Override
  protected Database getDatabase() {
    return new InFileDatabase(testDataBase, jsonHelper,
        new FileProcessor(), new IdGenerator(new FileProcessor()));
  }

  @Before
  public void cleaner() throws IOException {
    FileWriter fileWriter = new FileWriter(testDataBase);
    fileWriter.write("");
    fileWriter.close();
  }

  @Test
  public void shouldDeleteInvoiceFromBaseById() throws Exception {
    // given
    Database database = getDatabase();
    List<Invoice> invoicesInOrder = invoicesGenerator
        .genereataListOfInvoicesFromJanuary2016WithSuccessionId();

    for (Invoice invoice : invoicesInOrder) {
      database.saveInvoice(invoice);
    }

    // when
    database.deleteInvoice(7);

    // then
    assertTrue(database.getInvoices().size() == 29);
    assertEquals(6, (database.getInvoices().get(5).getId()));
    assertEquals(8, (database.getInvoices().get(6).getId()));

  }

  @Test
  public void shouldUpdateInvoice() throws Exception {

    // given
    Database database = getDatabase();
    List<Invoice> invoicesInOrder = invoicesGenerator
        .genereataListOfInvoicesFromJanuary2016WithSuccessionId();

    for (Invoice invoice : invoicesInOrder) {
      database.saveInvoice(invoice);
    }

    // when
    database.deleteInvoice(7);

    // then
    assertTrue(database.getInvoices().size() == 29);
    assertEquals(6, (database.getInvoices().get(5).getId()));
    assertEquals(8, (database.getInvoices().get(6).getId()));

  }
}




