package pl.coderstrust.database;

import pl.coderstrust.model.Invoice;

import java.util.Comparator;

public class MyComparator implements Comparator<Invoice> {

  @Override
  public int compare(Invoice i1, Invoice i2) {
    if (i1.getLocalDate().isBefore(i2.getLocalDate())) {
      return -1;
    } else if (i1.getLocalDate().isAfter(i2.getLocalDate())) {
      return 1;
    }
    return 0;
  }
}
