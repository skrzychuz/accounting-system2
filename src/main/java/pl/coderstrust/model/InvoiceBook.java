package pl.coderstrust.model;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.GeneratorId;

import java.util.List;

public class InvoiceBook {

  private final Database database;


  public InvoiceBook(Database database) {
    this.database = database;
  }

  public List<Invoice> getInvoices() throws Exception {
    return database.getInvoicesUnsorted();
  }

  public void addInvoices(Invoice invoice) throws Exception {
    invoice.setId(database.setUniqueId());
    database.saveInvoice(invoice);
  }

  public void deleteInvoice(int id) throws Exception {
    database.deleteInvoice(id);
  }

  public void modifyInvoice(int id, Invoice invoice) throws Exception {
    database.updateInvoice(id, invoice);
  }
}
