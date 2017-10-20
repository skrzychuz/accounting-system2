package pl.coderstrust.model.invoiceVisitorPattern;

import pl.coderstrust.model.invoiceModel.Invoice;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceBookVisitable {
  BigDecimal accept(InvoiceVisitor invoiceVisitor, List<Invoice> invoiceList);

}


