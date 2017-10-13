package pl.coderstrust.model.invoiceVisitorPattern;

import pl.coderstrust.model.invoiceModel.Invoice;

import java.math.BigDecimal;

public interface InvoiceVisitor {
  BigDecimal visit(Invoice invoice);
}
