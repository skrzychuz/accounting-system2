package pl.coderstrust.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceBook;

import java.util.List;

@RestController
public class InvoiceController {

  private InvoiceBook invoiceBookDatabase;

  @Autowired
  InvoiceController(Database database) {
    this.invoiceBookDatabase = new InvoiceBook(database);

  }

  @RequestMapping("/")
  public @ResponseBody
  String greeting() {
    return "Hello World";
  }

  @RequestMapping(value = "/invoices", method = RequestMethod.POST)
  public void saveInvoice(@RequestBody Invoice invoice) throws Exception {
    invoiceBookDatabase.addInvoices(invoice);
    System.out.println("success POST");

  }

  @RequestMapping(value = "/invoices", method = RequestMethod.GET)
  public List<Invoice> getInvoices() throws Exception {
    return invoiceBookDatabase.getInvoices();
  }

  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
  public Invoice getInvoices2(@PathVariable int id) throws Exception {
    return invoiceBookDatabase.getInvoices()
        .stream()
        .filter(invoice -> invoice.getId() == id)
        .findFirst()
        .get();
  }

  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.DELETE)
  public void removeInvoiceById(@PathVariable int id) throws Exception {
    invoiceBookDatabase.deleteInvoice(id);
  }
}

