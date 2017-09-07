package pl.coderstrust.model;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.memory.InMemoryDatabase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class TestMain {

  Database database;

  public static void main(String[] args) throws Exception {

    InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
    InFileDatabase inFileDatabase = new InFileDatabase();
    Invoice invoice1 = new Invoice(65, "yo",
        new Money(BigDecimal.valueOf(150), BigDecimal.TEN, Currency.PLN),
        LocalDate.of(2016, 6, 15));
    Invoice invoice2 = new Invoice(66, "yoyo",
        new Money(BigDecimal.valueOf(150), BigDecimal.TEN, Currency.PLN),
        LocalDate.of(2016, 7, 15));
    Invoice invoice3 = new Invoice(67, "yoyoyo",
        new Money(BigDecimal.valueOf(150), BigDecimal.TEN, Currency.PLN),
        LocalDate.of(2016, 5, 15));

    List<Invoice> testlist = new ArrayList<>();

    testlist.add(invoice1);
    testlist.add(invoice2);
    testlist.add(invoice3);

    inMemoryDatabase.sortingList(testlist);

    int i = 0;


    List <Invoice> listtest = inFileDatabase.getFromToListOfInvoices(LocalDate.of(2016,6,1), LocalDate.of(2016,6,30));
    int id = 0;
  }


}
