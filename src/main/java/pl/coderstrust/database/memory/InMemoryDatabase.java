package pl.coderstrust.database.memory;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.MyComparator;
import pl.coderstrust.model.Invoice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
  public List<Invoice> getFromToListOfInvoices(LocalDate fromDate, LocalDate toDate) {

    List<Invoice> fromToList = new ArrayList<>();
    for (Invoice invoice : listOfInovice) {
      if (invoice.getLocalDate().isAfter(fromDate) && invoice.getLocalDate().isBefore(toDate)) {
        fromToList.add(invoice);
      }
    }
    return fromToList;
  }

  @Override
  public List<Invoice> sortingList(List<Invoice> listToSort) {

    Collections.sort(listToSort, new MyComparator());

    return listToSort;

  }
}
