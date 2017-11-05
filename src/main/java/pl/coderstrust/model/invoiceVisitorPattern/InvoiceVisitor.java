package pl.coderstrust.model.invoiceVisitorPattern;

import org.springframework.stereotype.Service;
import pl.coderstrust.model.invoiceModel.Invoice;

import java.math.BigDecimal;
@Service
public interface InvoiceVisitor {
  BigDecimal visit(Invoice invoice);
}

// 1. If you create those as beans you can simplify your project and create single class with @configuration annotation
//    and return InvoiceVisitor from each methods / @bean - then you even don't need to declare classes -
//    it's enough to return anonymous classes from methods.
// 2. What does it do? @service on interface?
