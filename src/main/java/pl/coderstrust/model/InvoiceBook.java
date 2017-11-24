package pl.coderstrust.model;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.OperationResult;
import pl.coderstrust.model.invoiceModel.Invoice;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceBookVisitable;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceVisitable;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceVisitor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class InvoiceBook implements InvoiceBookVisitable {

  private final Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public List<Invoice> getInvoices() {
    return database.getInvoices();
  }

  public void addInvoices(Invoice invoice) throws Exception {
    invoice.setId(database.getNextInvoiceId());
    database.saveInvoice(invoice);
  }

  public OperationResult deleteInvoice(int id) throws Exception {
    OperationResult operationResult = database.deleteInvoice(id);
    return operationResult;
  }

  public OperationResult modifyInvoice(int id, Invoice invoice) throws Exception {
    OperationResult operationResult = database.updateInvoice(id, invoice);
    return operationResult;
  }

  public List<Invoice> filterInvoiceByDateFromGivenPeriod(LocalDate dateFrom, LocalDate dateTo) {
    return getInvoices()
        .stream()
        .filter(
            invoice -> dateFrom == null || invoice.getLocalDate().isAfter(dateFrom.minusDays(1)))
        .filter(invoice -> dateTo == null || invoice.getLocalDate().isBefore(dateTo.plusDays(1)))
        .collect(Collectors.toList());
  }

  @Override
  public BigDecimal accept(InvoiceVisitor invoiceVisitor, List<Invoice> invoicesList) {
    BigDecimal valueToReturn = BigDecimal.valueOf(0);
    for (Invoice invoice : invoicesList) {
      valueToReturn = valueToReturn.add(invoice.accept(invoiceVisitor));
    }
    return valueToReturn;
  }
}

