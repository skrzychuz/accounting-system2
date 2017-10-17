package pl.coderstrust.model;

import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;

import java.util.List;

@Service
public class InvoiceBook {

  private final Database database;


  public InvoiceBook(Database database) {
    this.database = database;
  }

  public List<Invoice> getInvoices() throws Exception {
    return database.getInvoices();
  }

  public void addInvoices(Invoice invoice) throws Exception {
    invoice.setId(database.getNextInvoiceId());
    database.saveInvoice(invoice);
  }

  public void deleteInvoice(int id) throws Exception {
    database.deleteInvoice(id);
  }

  public void modifyInvoice(int id, Invoice invoice) throws Exception {
    database.updateInvoice(id, invoice);
  }
}
