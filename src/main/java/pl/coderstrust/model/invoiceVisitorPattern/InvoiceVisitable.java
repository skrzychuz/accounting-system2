package pl.coderstrust.model.invoiceVisitorPattern;

import java.math.BigDecimal;

public interface InvoiceVisitable {
  BigDecimal accept(InvoiceVisitor invoiceVisitor);

}


