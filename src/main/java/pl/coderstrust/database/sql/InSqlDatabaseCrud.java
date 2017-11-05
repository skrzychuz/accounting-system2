package pl.coderstrust.database.sql;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.invoiceModel.Entry;
import pl.coderstrust.model.invoiceModel.Invoice;

import javax.transaction.Transactional;

@Repository
@Transactional
@ConditionalOnProperty(name = "pl.coderstrust.database", havingValue = "inSqlDatabaseCrud")
public class InSqlDatabaseCrud implements Database {

  @Autowired
  private InvoiceCrud invoiceCrud;

  @Override
  public void saveInvoice(Invoice invoice) {
    for (Entry entry : invoice.getEntries()) {
      entry.setInvoice(invoice);
    }
    invoiceCrud.save(invoice);
  }

  @Override
  public List<Invoice> getInvoices() {
    return (List<Invoice>) invoiceCrud.findAll();
  }

  @Override
  public List<Invoice> getListOfInvoicesFromGivenPeriod(LocalDate fromDate, LocalDate toDate) {
    // return invoiceCrud.findByLocalDateBetween(fromDate, toDate);
    return null;
  }

  @Override
  public int getNextInvoiceId() {
    return 0;
  }

  @Override
  public void deleteInvoice(int id) {
    invoiceCrud.delete(id);
  }

  @Override
  public void updateInvoice(int id, Invoice invoice) {
    updateAllIdInInvoice(id, invoice);
    invoiceCrud.save(invoice);
  }

  private void updateAllIdInInvoice(int id, Invoice invoice) {
    invoice.setId(id);
    invoice.getSeller().setId(id);
    invoice.getBuyer().setId(id);
  }
}
