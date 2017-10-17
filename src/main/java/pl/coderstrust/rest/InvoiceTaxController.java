package pl.coderstrust.rest;

import javax.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.coderstrust.model.InvoiceBook;
import pl.coderstrust.model.invoiceModel.Invoice;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceCost;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceIncome;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceInputVat;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceOutputVat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class InvoiceTaxController {

  private InvoiceBook invoiceBookDatabase;
  private InvoiceIncome invoiceIncome;
  private InvoiceCost invoiceCost;
  private InvoiceOutputVat invoiceOutputVat;
  private InvoiceInputVat invoiceInputVat;


  InvoiceTaxController(InvoiceBook invoiceBook,
      InvoiceIncome invoiceIncome,
      InvoiceCost invoiceCost,
      InvoiceOutputVat invoiceOutputVat,
      InvoiceInputVat invoiceInputVat) {
    this.invoiceBookDatabase = invoiceBook;
    this.invoiceIncome = invoiceIncome;
    this.invoiceCost = invoiceCost;
    this.invoiceOutputVat = invoiceOutputVat;
    this.invoiceInputVat = invoiceInputVat;

  }

  @RequestMapping(value = "/invoices/income", method = RequestMethod.GET)
  public BigDecimal getIncome(
      @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateFrom,
      @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateTo)
      throws Exception {
    return invoiceBookDatabase
        .accept(invoiceIncome,
            invoiceBookDatabase.filterInvoiceByDateFromGivenPeriod(dateFrom, dateTo));
  }

  @RequestMapping(value = "/invoices/cost", method = RequestMethod.GET)
  public BigDecimal getCost(
      @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateFrom,
      @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateTo)
      throws Exception {
    return invoiceBookDatabase
        .accept(invoiceCost,
            invoiceBookDatabase.filterInvoiceByDateFromGivenPeriod(dateFrom, dateTo));
  }

  @RequestMapping(value = "/invoices/inputvat", method = RequestMethod.GET)
  public BigDecimal getInputVat(
      @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateFrom,
      @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateTo)
      throws Exception {
    return invoiceBookDatabase
        .accept(invoiceInputVat,
            invoiceBookDatabase.filterInvoiceByDateFromGivenPeriod(dateFrom, dateTo));
  }

  @RequestMapping(value = "/invoices/outputvat", method = RequestMethod.GET)
  public BigDecimal getOutputVat(
      @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateFrom,
      @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateTo)
      throws Exception {
    return invoiceBookDatabase
        .accept(invoiceOutputVat,
            invoiceBookDatabase.filterInvoiceByDateFromGivenPeriod(dateFrom, dateTo));
  }
}


