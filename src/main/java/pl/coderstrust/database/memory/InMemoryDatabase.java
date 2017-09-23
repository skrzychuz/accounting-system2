package pl.coderstrust.database.memory;

import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Invoice.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryDatabase implements Database {

  private final List<Invoice> listOfInovice = new ArrayList<>();

  @Override
  public void saveInvoice(Invoice invoice) {
    listOfInovice.add(invoice);
  }

  @Override
  public List<Invoice> getInvoices() {
    Collections.sort(listOfInovice);
    return Collections.unmodifiableList(listOfInovice);
  }

  @Override
  public List<Invoice> getInvoicesUnsorted() {
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
  public void setUniqueId(Invoice invoice) {

    if (getInvoicesUnsorted().size() == 0) {
      invoice.setId(0);
    } else {
      invoice.setId((listOfInovice.get(listOfInovice.size() - 1).getId()) + 1);
    }
  }
}
