package pl.coderstrust.model.invoiceVisitorPattern;

import pl.coderstrust.model.invoiceModel.Invoice;

import java.math.BigDecimal;

public class InvoiceCost implements InvoiceVisitor {

  @Override
  public BigDecimal visit(Invoice invoice) {
    if (!invoice.getSeller().getName().equals("MyCompany")) {
      return invoice.getAmount();
    } else {
      return BigDecimal.valueOf(0);
    }
  }
}