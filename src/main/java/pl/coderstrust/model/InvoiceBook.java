package pl.coderstrust.model;

import pl.coderstrust.database.Database;
import pl.coderstrust.model.invoiceModel.Invoice;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceVisitable;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceVisitor;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceBook implements InvoiceVisitable {

  private final Database database;


  public InvoiceBook(Database database) {
    this.database = database;
  }

  public List<Invoice> getInvoices() {
    return database.getInvoices();
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

  @Override
  public BigDecimal accept(InvoiceVisitor invoiceVisitor) {
    BigDecimal valueToReturn = BigDecimal.valueOf(0);
    for (Invoice invoice : getInvoices()) {
      valueToReturn = valueToReturn.add(invoice.accept(invoiceVisitor));
    }
    return valueToReturn;
  }
}

