package pl.coderstrust.database.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class InFileDatabase implements Database {

  private final File myFileDatabase;
  private final JsonHelper jsonHelper;
  private final FileProcessor fileProcessor;


  /**
   * Constructor.
   */

  public InFileDatabase(String path, JsonHelper jsonHelper, FileProcessor fileProcessor) {
    this.jsonHelper = jsonHelper;
    this.fileProcessor = fileProcessor;
    this.myFileDatabase = new File(path);
  }

  @Autowired
  public InFileDatabase(JsonHelper jsonHelper, FileProcessor fileProcessor) {
    this("database\\data.json", jsonHelper, fileProcessor);
  }

  @Override
  public void saveInvoice(Invoice invoice) {

    fileProcessor.saveToFile(jsonHelper.jsonConvertInvoiceToString(invoice), myFileDatabase);
  }

  @Override
  public List<Invoice> getInvoices() {

    List<Invoice> listToSort = jsonHelper
        .jsonConvertFromStrinfToInvoice(fileProcessor.readFromFile(myFileDatabase));
    Collections.sort(listToSort);
    return listToSort;
  }

  @Override
  public List<Invoice> getInvoicesUnsorted() {
    return jsonHelper.jsonConvertFromStrinfToInvoice(fileProcessor.readFromFile(myFileDatabase));
  }

  @Override
  public List<Invoice> getListOfInvoicesFromGivenPeriod(LocalDate fromDate, LocalDate toDate) {
    List<Invoice> invoicesList = getInvoices();
    List<Invoice> partOfList = new ArrayList<>();

    for (Invoice invoice : invoicesList) {
      if (invoice.getLocalDate().isAfter(fromDate.minusDays(1)) && invoice.getLocalDate()
          .isBefore(toDate.plusDays(1))) {
        partOfList.add(invoice);
      }
    }
    return partOfList;
  }

  @Override
  public void setUniqueId(Invoice invoice) {
    List<Invoice> listOfInvoices = getInvoicesUnsorted();
    if (listOfInvoices.size() == 0) {
      invoice.setId(0);
    } else {
      invoice.setId((listOfInvoices.get(listOfInvoices.size() - 1).getId()) + 1);
    }
  }

  @Override
  public void deleteInvoice(int id) {

    Iterator<Invoice> invoiceIterator = getInvoicesUnsorted().iterator();
    fileProcessor.clearTheFile(myFileDatabase);
    while (invoiceIterator.hasNext()) {
      Invoice invoice = invoiceIterator.next();
      if (invoice.getId() == id) {
        invoiceIterator.remove();

      } else {
        saveInvoice(invoice);
      }
    }
  }

  @Override
  public void updateInvoice(int id, Invoice invoice) {

    Iterator<Invoice> invoiceIterator = getInvoicesUnsorted().iterator();
    fileProcessor.clearTheFile(myFileDatabase);
    while (invoiceIterator.hasNext()) {
      Invoice in = invoiceIterator.next();
      if (in.getId() == id) {
        invoiceIterator.remove();
        invoice.setId(id);
        saveInvoice(invoice);

      } else {
        invoice.setId(id);
        saveInvoice(in);
      }
    }
  }
}

