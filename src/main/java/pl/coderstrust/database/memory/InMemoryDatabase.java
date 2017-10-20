package pl.coderstrust.database.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.invoiceModel.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@ConditionalOnProperty(name = "pl.coderstrust.database", havingValue = "inMemoryDatabase")
public class InMemoryDatabase implements Database {

  private final List<Invoice> listOfInovice = new ArrayList<>();

  @Override
  public void saveInvoice(Invoice invoice) {
    listOfInovice.add(invoice);
  }

  @Override
  public List<Invoice> getInvoices() {
    return Collections.unmodifiableList(listOfInovice);
  }


  @Override
  public List<Invoice> getListOfInvoicesFromGivenPeriod(LocalDate fromDate, LocalDate toDate) {
    return listOfInovice.stream()
        .filter(invoice -> invoice.getLocalDate().isAfter(fromDate.minusDays(1))
            && invoice.getLocalDate().isBefore(toDate.plusDays(1)))
        .collect(Collectors.toList());
  }

  @Override
  public int getNextInvoiceId() {
    return 0;
  }

  @Override
  public void deleteInvoice(int id) {

    Iterator<Invoice> invoiceIterator = this.getInvoices().iterator();
    while (invoiceIterator.hasNext()) {
      if (invoiceIterator.next().getId() == id) {
        invoiceIterator.remove();
        break;
      }
    }
  }

  @Override
  public void updateInvoice(int id, Invoice invoice) {

  }
}
