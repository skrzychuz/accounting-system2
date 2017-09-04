package pl.coderstrust.model;

import pl.coderstrust.database.Database;

import java.util.ArrayList;
import java.util.List;

public class InvoiceBook {

  private final Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public List<Invoice> getInvoices() {
    return database.getInvoices();
  }

  public void addInvoice(Invoice invoice) {
    database.saveInvoice(invoice);
  }
}
