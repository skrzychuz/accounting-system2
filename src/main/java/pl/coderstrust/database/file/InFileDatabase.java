package pl.coderstrust.database.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;

import java.io.File;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InFileDatabase implements Database {

  private final File myFileDatabase;
  private final JsonHelper jsonHelper;
  private final FileProcessor fileProcessor;
  private final GeneratorId generatorId;


  /**
   * Constructor.
   */
  public InFileDatabase(File path, JsonHelper jsonHelper, FileProcessor fileProcessor,
      GeneratorId generatorId) {
    this.jsonHelper = jsonHelper;
    this.fileProcessor = fileProcessor;
    this.generatorId = generatorId;
    this.myFileDatabase = path;
  }

  @Autowired
  public InFileDatabase(JsonHelper jsonHelper, FileProcessor fileProcessor,
      GeneratorId generatorId) {
    this(new File("database\\data.json"), jsonHelper, fileProcessor, generatorId);
  }

  @Override
  public void saveInvoice(Invoice invoice) {
    fileProcessor
        .saveToFile(jsonHelper.convertInvoiceObjectToJsonAsString(invoice), myFileDatabase);
  }

  @Override
  public List<Invoice> getInvoices() {
    List<Invoice> listToSort = jsonHelper
        .convertListOfStringsRepresentingInvoiceAsJsonToListOfInvoices(
            fileProcessor.readFromFile(myFileDatabase));
    return listToSort;
  }


  @Override
  public List<Invoice> getListOfInvoicesFromGivenPeriod(LocalDate fromDate, LocalDate toDate) {
    List<Invoice> invoicesList = getInvoices();
    List<Invoice> partOfList;

    partOfList = invoicesList.stream()
        .filter(invoice ->
            ((invoice.getLocalDate().isAfter(fromDate.minusDays(1)))
                && (invoice.getLocalDate()
                .isBefore(toDate.plusDays(1)))))
        .collect(Collectors.toList());

    return partOfList;
  }

  @Override
  public int setUniqueId() {
    return (generatorId.generateNewId());
  }


  @Override
  public void deleteInvoice(int id) {

    Iterator<Invoice> invoiceIterator = getInvoices().iterator();
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

    Iterator<Invoice> invoiceIterator = getInvoices().iterator();
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

