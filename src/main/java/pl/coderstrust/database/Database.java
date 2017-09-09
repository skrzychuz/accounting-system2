package pl.coderstrust.database;

import pl.coderstrust.model.Invoice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface Database {

  void saveInvoice(Invoice invoice) throws IOException;

  List<Invoice> getInvoices() throws Exception;

  List<Invoice> getListOfInvoicesFromPeriodTime(LocalDate fromDate, LocalDate toDate) throws Exception;

  List<Invoice> sortingList(List<Invoice> listToSort);

  void clearDatabase();

}
