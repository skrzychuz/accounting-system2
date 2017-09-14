package pl.coderstrust.model;

import pl.coderstrust.database.Database;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class InvoiceBook {


  private final Database database;


  public InvoiceBook(Database database) {
    this.database = database;
  }

  public List<Invoice> getInvoices() throws Exception {
    return database.getInvoices();
  }

  public void addInvoices(Invoice invoice) throws Exception {
    database.saveInvoice(invoice);
  }

  public List<Invoice> getListOfInvoicesFromGivenPeriod (LocalDate fromDate, LocalDate toDate)
      throws Exception {
    return database.getListOfInvoicesFromGivenPeriod(fromDate, toDate);
  }

}