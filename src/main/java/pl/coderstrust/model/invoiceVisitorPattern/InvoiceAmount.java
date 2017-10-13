package pl.coderstrust.model.invoiceVisitorPattern;

import pl.coderstrust.model.invoiceModel.Invoice;
import java.math.BigDecimal;

public class InvoiceAmount implements InvoiceVisitor {

  @Override
  public BigDecimal visit(Invoice invoice) {
    return invoice.getAmount();
  }
}
