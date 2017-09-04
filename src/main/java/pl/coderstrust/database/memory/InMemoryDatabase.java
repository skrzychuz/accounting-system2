package pl.coderstrust.database.memory;

import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;

import java.util.ArrayList;
import java.util.Collections;
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
}
