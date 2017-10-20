package pl.coderstrust.model.invoiceVisitorPattern;

import org.springframework.stereotype.Service;
import pl.coderstrust.model.invoiceModel.Invoice;

import java.math.BigDecimal;
@Service
public interface InvoiceVisitor {
  BigDecimal visit(Invoice invoice);
}
