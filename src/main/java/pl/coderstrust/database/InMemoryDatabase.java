package pl.coderstrust.database;

import pl.coderstrust.model.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase implements Database {

  private final List<Invoice> listOfInovice = new ArrayList<>();


  @Override
  public void saveInvoice(Invoice invoice) {
    listOfInovice.add(invoice);

  }

  @Override
  public List<Invoice> getListOfInvoice() {
    return listOfInovice;
  }
}
