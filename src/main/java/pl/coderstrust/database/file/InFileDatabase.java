package pl.coderstrust.database.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.invoiceModel.Invoice;

import java.io.File;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "pl.coderstrust.database", havingValue = "inFileDatabase")

public class InFileDatabase implements Database {

  private final File myFileDatabase;
  private final JsonHelper jsonHelper;
  private final FileProcessor fileProcessor;
  private final IdGenerator idGenerator;

  /**
   * Constructor.
   */
  @Autowired
  public InFileDatabase(@Value("${pl.coderstrust.file.path}")
      String filePath, JsonHelper jsonHelper, FileProcessor fileProcessor,
      IdGenerator generatorId) {

    this.jsonHelper = jsonHelper;
    this.fileProcessor = fileProcessor;
    this.idGenerator = generatorId;
    this.myFileDatabase = new File(filePath);
  }

  @Override
  public void saveInvoice(Invoice invoice) {

    fileProcessor
        .saveToFile(jsonHelper.convertInvoiceObjectToJsonAsString(invoice), myFileDatabase);
  }

  @Override
  public List<Invoice> getInvoices() {
    return jsonHelper.convertListOfStringsRepresentingInvoiceAsJsonToListOfInvoices(
        fileProcessor.readFromFile(myFileDatabase));
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
  public int getNextInvoiceId() {
    return (idGenerator.generateNewId());
  }

  @Override
  public void deleteInvoice(int id) {

    Iterator<Invoice> invoiceIterator = this.getInvoices().iterator();
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

    Iterator<Invoice> invoiceIterator = this.getInvoices().iterator();
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

