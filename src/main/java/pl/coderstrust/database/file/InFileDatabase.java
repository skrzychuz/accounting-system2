package pl.coderstrust.database.file;

import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InFileDatabase implements Database {

  private final File myFileDatabase;
  private final JsonHelper jsonHelper;
  private final FileProcessor fileProcessor;

  /**
   * Constructor.
   */
  InFileDatabase(String path, JsonHelper jsonHelper, FileProcessor fileProcessor) {
    this.jsonHelper = jsonHelper;
    this.fileProcessor = fileProcessor;
    this.myFileDatabase = new File(path);
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

  private List<Invoice> getInvoicesUnsorted() {
    return jsonHelper.jsonConvertFromStrinfToInvoice(fileProcessor.readFromFile(myFileDatabase));
  }

  @Override
  public List<Invoice> getListOfInvoicesFromGivenPeriod(LocalDate fromDate, LocalDate toDate)
      throws IOException {

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
  public void setUniqId(Invoice invoice) {
    List<Invoice> listOfInvoices = getInvoicesUnsorted();
    if (listOfInvoices.size() == 0) {
      invoice.setId(0);
    } else {
      invoice.setId((listOfInvoices.get(listOfInvoices.size() - 1).getId()) + 1);
    }
  }
}
