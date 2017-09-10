package pl.coderstrust.database;

import pl.coderstrust.model.Invoice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface Database {

  void saveInvoice(Invoice invoice) throws IOException;

  List<Invoice> getInvoices() throws Exception;

  List<Invoice> getListOfInvoicesFromPeriod(LocalDate fromDate, LocalDate toDate) throws Exception;

}
