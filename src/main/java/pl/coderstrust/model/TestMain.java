package pl.coderstrust.model;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.model.Invoice.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestMain {

  Database database;

  public static void main(String[] args) throws Exception {

    InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
    InFileDatabase inFileDatabase = new InFileDatabase();

//    Invoice invoice1 = new Invoice(65, "yo", BigDecimal.valueOf(150), BigDecimal.valueOf(23),LocalDate.of(2016, 6, 15));
//    Invoice invoice2 = new Invoice(65, "yo", BigDecimal.valueOf(150), BigDecimal.valueOf(23),LocalDate.of(2016, 5, 15));
//    Invoice invoice3 = new Invoice(65, "yo", BigDecimal.valueOf(150), BigDecimal.valueOf(23),LocalDate.of(2016, 7, 15));

    Invoice invoice1 = new Builder()
        .withId(5)
        .withLocalDate(LocalDate.of(2016, 7, 15))
        .build();

    Invoice invoice2 = new Builder()
        .withId(5)
        .withLocalDate(LocalDate.of(2016, 5, 15))
        .build();

    Invoice invoice3 = new Builder()
        .withId(5)
        .withLocalDate(LocalDate.of(2016, 6, 15))
        .build();

    List<Invoice> testlist = new ArrayList<>();

    testlist.add(invoice1);
    testlist.add(invoice2);
    testlist.add(invoice3);

    // inMemoryDatabase.sortingList(testlist);

    inMemoryDatabase.saveInvoice(invoice1);
    inMemoryDatabase.saveInvoice(invoice2);
    inMemoryDatabase.saveInvoice(invoice3);

    int i = 0;

    List<Invoice> listtest = inFileDatabase
        .getListOfInvoicesFromPeriod(LocalDate.of(2016, 6, 1), LocalDate.of(2016, 6, 30));
    int j = 0;

    List<Invoice> listtest2 = inMemoryDatabase
        .getListOfInvoicesFromPeriod(LocalDate.of(2016, 6, 1), LocalDate.of(2016, 6, 30));
    int k = 0;
  }


}
