package pl.coderstrust.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceBook;


@RestController
public class InvoiceController {


  private InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
  private InvoiceBook invoiceBook = new InvoiceBook(inMemoryDatabase);

  @RequestMapping(value = "/start", method = RequestMethod.GET)
  public List<Invoice> getInvoices() throws Exception {
    return invoiceBook.getInvoices();

  }

  @RequestMapping(value = "/start", method = RequestMethod.POST)
  public void saveInvoice(@RequestBody Invoice invoice) throws Exception {
    invoiceBook.addInvoices(invoice);
    System.out.println("success");

  }
}
