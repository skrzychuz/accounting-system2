package pl.coderstrust.model;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.FileProcessor;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.file.JsonHelper;
import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.model.Invoice.Builder;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestMain {

  Database database;

  /**
   *
   */
  public static void main(String[] args) throws Exception {

    // InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();

//    File fileForTests = new File("src\\test\\resources\\dataForTest.json");
//    String testPath = fileForTests.getPath();
//    InFileDatabase inFileDatabase = new InFileDatabase(testPath, new JsonHelper(),
//        new FileProcessor());
//
//    Invoice invoice1 = new Builder()
//        .withLocalDate(LocalDate.of(2016, 7, 15))
//        .build();
//
//    Invoice invoice2 = new Builder()
//        .withLocalDate(LocalDate.of(2016, 5, 15))
//        .build();
//
//    Invoice invoice3 = new Builder()
//        .withLocalDate(LocalDate.of(2016, 6, 15))
//        .build();
//
//    InvoiceBook invoiceBook = new InvoiceBook(inFileDatabase);
//
//    invoiceBook.addInvoices(invoice1);
//    invoiceBook.addInvoices(invoice2);
//    invoiceBook.addInvoices(invoice3);
//
//    // inMemoryDatabase.sortingList(testlist);
//
////    inMemoryDatabase.saveInvoice(invoice1);
////    inMemoryDatabase.saveInvoice(invoice2);
////    inMemoryDatabase.saveInvoice(invoice3);
////
////    int i = 0;
////
////    List<Invoice> listtest = inFileDatabase
////        .getListOfInvoicesFromGivenPeriod(LocalDate.of(2016, 6, 1), LocalDate.of(2016, 6, 30));
////    int j = 0;
////
////    List<Invoice> listtest2 = inMemoryDatabase
////        .getListOfInvoicesFromGivenPeriod(LocalDate.of(2016, 6, 1), LocalDate.of(2016, 6, 30));
////    int k = 0;
//
//
  }
}
