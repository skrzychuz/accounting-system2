package pl.coderstrust.rest;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.coderstrust.database.Database;
import pl.coderstrust.model.invoiceModel.Invoice;
import pl.coderstrust.model.InvoiceBook;

import java.util.List;

@RestController
public class InvoiceController {

  private InvoiceBook invoiceBookDatabase;

  @Autowired
  InvoiceController(InvoiceBook invoiceBook) {
    this.invoiceBookDatabase = invoiceBook;
  }

  /**
   * Save.
   */
  @RequestMapping(value = "/invoices", method = RequestMethod.POST)
  public void saveInvoice(@RequestBody Invoice invoice) throws Exception {
    invoiceBookDatabase.addInvoices(invoice);
  }

  @RequestMapping(value = "/invoices", method = RequestMethod.GET)
  public List<Invoice> getInvoices() throws Exception {
    return invoiceBookDatabase.getInvoices();
  }

  /**
   * Get.
   */
  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
  public Invoice getInvoicesById(@PathVariable int id) throws Exception {
    return invoiceBookDatabase.getInvoices()
        .stream()
        .filter(invoice -> invoice.getId() == id)
        .findFirst()
        .orElse(null);
  }

  /**
   * Delete.
   */
  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> removeInvoiceById(@PathVariable int id) throws Exception {
    invoiceBookDatabase.deleteInvoice(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Update.
   */
  @RequestMapping(value = "/invoices/{id}", method = RequestMethod.PUT)
  public ResponseEntity<?> updateInvoice(@PathVariable Integer id, @RequestBody Invoice invoice)
      throws Exception {
    invoiceBookDatabase.modifyInvoice(id, invoice);
    return ResponseEntity.noContent().build();
  }
}

