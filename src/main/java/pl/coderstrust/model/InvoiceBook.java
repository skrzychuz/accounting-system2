package pl.coderstrust.model;

import pl.coderstrust.database.Database;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InvoiceBook {


  private final Database database;


  public InvoiceBook(Database database) {
    this.database = database;
  }

  public List<Invoice> getInvoicesIB() throws Exception {
    return database.getInvoices();
  }

  public void addInvoiceIB(Invoice invoice) throws IOException {
    database.saveInvoice(invoice);
  }

  public List<Invoice> getFromToListOfInvoicesInBook(LocalDate fromDate, LocalDate toDate)
      throws Exception {
    return database.getFromToListOfInvoices(fromDate, toDate);
  }

  public List<Invoice> sortingListInBook(List<Invoice> listToSort) {

    return database.sortingList(listToSort);
  }
}