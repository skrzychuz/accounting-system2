package pl.coderstrust.database.memory;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.MyComparator;
import pl.coderstrust.model.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    return Collections.unmodifiableList(listOfInovice);
  }

  @Override
  public List<Invoice> getListOfInvoicesFromPeriodTime(LocalDate fromDate, LocalDate toDate) {
    return listOfInovice.stream()
        .filter(invoice -> invoice.getLocalDate().isAfter(fromDate) && invoice.getLocalDate()
            .isBefore(toDate)) //
        .collect(Collectors.toList());
  }

  @Override
  public List<Invoice> sortingList(List<Invoice> listToSort) {

    Collections.sort(listToSort, new MyComparator());

    return listToSort;

  }

  @Override
  public void clearDatabase() {

  }
}
