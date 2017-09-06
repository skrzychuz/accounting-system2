package pl.coderstrust.model;

import pl.coderstrust.database.Database;

import java.io.IOException;
import java.util.List;

public class InvoiceBook {

  private final Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public List<Invoice> getInvoices() throws Exception {
    return database.getInvoices();
  }

  public void addInvoice(Invoice invoice) throws IOException {
    database.saveInvoice(invoice);
  }
}
