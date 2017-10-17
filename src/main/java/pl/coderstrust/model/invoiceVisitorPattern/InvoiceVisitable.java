package pl.coderstrust.model.invoiceVisitorPattern;

import pl.coderstrust.model.invoiceModel.Invoice;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceVisitable {
    BigDecimal accept(InvoiceVisitor invoiceVisitor);

}


