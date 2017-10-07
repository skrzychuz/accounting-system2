package pl.coderstrust.database.file;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;


import org.junit.Before;
import org.junit.Test;
import pl.coderstrust.InvoicesGenerator;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.DatabaseTestAbstract;
import pl.coderstrust.model.invoiceModel.Invoice;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InFileDatabaseTest extends DatabaseTestAbstract {

  FileProcessor fileProcessorMock = mock(FileProcessor.class);
  JsonHelper jsonHelperMock = mock(JsonHelper.class);
  File fileForTests = new File("src\\test\\resources\\dataForTest.json");
  public String testPath = fileForTests.getPath();
  File fileidtest = new File("src\\test\\resources\\idtest.json");
  DatabaseFilesPaths databaseFilesPaths = new DatabaseFilesPaths();
  InvoicesGenerator invoicesGenerator = new InvoicesGenerator();
  MapperConfig mapperConfig = new MapperConfig();


  @Override
  protected Database getDatabase() {
    return new InFileDatabase(databaseFilesPaths.testDataBase, new JsonHelper(),
        new FileProcessor(), new GeneratorId(new FileProcessor()));
  }

  @Before
  public void cleaner() throws IOException {
    FileWriter fileWriter = new FileWriter(testPath);
    fileWriter.write("");
    fileWriter.close();
  }

  @Test
  public void shouldDeleteInvoiceFromBaseById() throws Exception {
    // given
    cleaner();
    Database database = getDatabase();
    List<Invoice> invoicesInOrder = invoicesGenerator
        .invoiceGeneratorFor30DaysInJanuary2016InSuccessionWithID();

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
    cleaner();
    Database database = getDatabase();
    List<Invoice> invoicesInOrder = invoicesGenerator
        .invoiceGeneratorFor30DaysInJanuary2016InSuccessionWithID();

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




