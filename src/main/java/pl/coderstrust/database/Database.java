package pl.coderstrust.database;

import pl.coderstrust.model.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface Database {

  void saveInvoice(Invoice invoice);

  List<Invoice> getInvoices();

  List<Invoice> getInvoicesUnsorted();

  List<Invoice> getListOfInvoicesFromGivenPeriod(LocalDate fromDate, LocalDate toDate);

  void setUniqueId(Invoice invoice);

  void deleteInvoice(int id);

}
