package pl.coderstrust.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.coderstrust.model.Invoice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface Database {

  void saveInvoice(Invoice invoice) throws Exception;

  List<Invoice> getInvoices() throws Exception;

  List<Invoice> getListOfInvoicesFromGivenPeriod(LocalDate fromDate, LocalDate toDate) throws Exception;

}
