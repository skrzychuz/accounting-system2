package pl.coderstrust.database;

import pl.coderstrust.model.invoiceModel.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface Database {

  void saveInvoice(Invoice invoice);

  List<Invoice> getInvoices();

  List<Invoice> getListOfInvoicesFromGivenPeriod(LocalDate fromDate, LocalDate toDate);

  int getNextInvoiceId();

  void deleteInvoice(int id);

  void updateInvoice(int id, Invoice invoice);

}
