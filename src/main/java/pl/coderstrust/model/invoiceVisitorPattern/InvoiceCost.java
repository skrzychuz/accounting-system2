package pl.coderstrust.model.invoiceVisitorPattern;

import org.springframework.stereotype.Service;
import pl.coderstrust.model.invoiceModel.Invoice;

import java.math.BigDecimal;

@Service
public class InvoiceCost implements InvoiceVisitor {

  @Override
  public BigDecimal visit(Invoice invoice) {

    return !invoice.getSeller().getName().equals("MyCompany") ? invoice.getAmount()
        : BigDecimal.valueOf(0);
  }
}