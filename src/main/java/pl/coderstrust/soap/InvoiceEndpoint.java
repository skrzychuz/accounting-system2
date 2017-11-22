package pl.coderstrust.soap;

import generatedFromXSD.AddInvoiceRequest;
import generatedFromXSD.AddInvoiceResponse;
import generatedFromXSD.DeleteInvoiceRequest;
import generatedFromXSD.DeleteInvoiceResponse;
import generatedFromXSD.GetAllInvoicesRequest;
import generatedFromXSD.GetAllInvoicesResponse;
import generatedFromXSD.GetCostRequest;
import generatedFromXSD.GetCostResponse;
import generatedFromXSD.GetIncomeRequest;
import generatedFromXSD.GetIncomeResponse;
import generatedFromXSD.GetInputVatRequest;
import generatedFromXSD.GetInputVatResponse;
import generatedFromXSD.GetOutputVatRequest;
import generatedFromXSD.GetOutputVatResponse;
import generatedFromXSD.UpdateInvoiceRequest;
import generatedFromXSD.UpdateInvoiceResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import generatedFromXSD.GetInovicesRequest;
import generatedFromXSD.GetInvoicesResponse;
import pl.coderstrust.model.InvoiceBook;
import pl.coderstrust.model.invoiceModel.Invoice;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceCost;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceIncome;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceInputVat;
import pl.coderstrust.model.invoiceVisitorPattern.InvoiceOutputVat;

import java.time.LocalDate;
import java.util.List;

@Endpoint
public class InvoiceEndpoint {

  private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";


  private InvoiceBook invoiceBook;
  private XmlInvoiceAdapter xmlInvoiceAdapter;
  private XmlDataAdapter xmlDataAdapter = new XmlDataAdapter();
  private InvoiceIncome invoiceIncome;
  private InvoiceCost invoiceCost;
  private InvoiceOutputVat invoiceOutputVat;
  private InvoiceInputVat invoiceInputVat;


  @Autowired
  public InvoiceEndpoint(InvoiceBook invoiceBook,
      InvoiceIncome invoiceIncome,
      InvoiceCost invoiceCost,
      InvoiceOutputVat invoiceOutputVat,
      InvoiceInputVat invoiceInputVat,
      XmlInvoiceAdapter xmlInvoiceAdapter) {
    this.invoiceBook = invoiceBook;
    this.invoiceIncome = invoiceIncome;
    this.invoiceCost = invoiceCost;
    this.invoiceOutputVat = invoiceOutputVat;
    this.invoiceInputVat = invoiceInputVat;
    this.xmlInvoiceAdapter = xmlInvoiceAdapter;

  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getInovicesRequest")
  @ResponsePayload
  public GetInvoicesResponse getInvoiceById(@RequestPayload GetInovicesRequest request)
      throws DatatypeConfigurationException {
    GetInvoicesResponse response = new GetInvoicesResponse();
    Invoice invoice = invoiceBook.getInvoices().get(request.getId() - 1);
    response.setInvoice(xmlInvoiceAdapter.toXMLInvoice(invoice));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllInvoicesRequest")
  @ResponsePayload
  public GetAllInvoicesResponse getAllInvoices(@RequestPayload GetAllInvoicesRequest request)
      throws DatatypeConfigurationException {
    GetAllInvoicesResponse response = new GetAllInvoicesResponse();
    List<Invoice> invoiceList = invoiceBook.getInvoices();

    for (Invoice invoice : invoiceList) {
      response.getAllInvoices().add(xmlInvoiceAdapter.toXMLInvoice(invoice));
    }

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addInvoiceRequest")
  @ResponsePayload
  public AddInvoiceResponse addInvoice(@RequestPayload AddInvoiceRequest request) throws Exception {
    AddInvoiceResponse response = new AddInvoiceResponse();
    Invoice invoice = new Invoice();
    invoice = xmlInvoiceAdapter.xmlToInvoice(request.getInvoice());
    invoiceBook.addInvoices(invoice);

    response.setId(invoice.getId());

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteInvoiceRequest")
  @ResponsePayload
  public DeleteInvoiceResponse deleteInvoice(@RequestPayload DeleteInvoiceRequest request)
      throws Exception {
    DeleteInvoiceResponse response = new DeleteInvoiceResponse();
    request.getId();
    invoiceBook.deleteInvoice(request.getId());

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateInvoiceRequest")
  @ResponsePayload
  public UpdateInvoiceResponse updateInvoice(@RequestPayload UpdateInvoiceRequest request)
      throws Exception {
    UpdateInvoiceResponse response = new UpdateInvoiceResponse();
    request.getId();
    invoiceBook
        .modifyInvoice(request.getId(), xmlInvoiceAdapter.xmlToInvoice(request.getInvoice()));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIncomeRequest")
  @ResponsePayload
  public GetIncomeResponse getIncome(@RequestPayload GetIncomeRequest request)
      throws Exception {
    GetIncomeResponse response = new GetIncomeResponse();
    LocalDate from = xmlDataAdapter.convertToLocalDate(request.getDateFrom());
    LocalDate to = xmlDataAdapter.convertToLocalDate(request.getDateTo());

    response.setIncome(invoiceBook.accept(invoiceIncome,
        invoiceBook.filterInvoiceByDateFromGivenPeriod(from, to)));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCostRequest")
  @ResponsePayload
  public GetCostResponse getCost(@RequestPayload GetCostRequest request)
      throws Exception {
    GetCostResponse response = new GetCostResponse();
    LocalDate from = xmlDataAdapter.convertToLocalDate(request.getDateFrom());
    LocalDate to = xmlDataAdapter.convertToLocalDate(request.getDateTo());

    response.setCost(invoiceBook.accept(invoiceIncome,
        invoiceBook.filterInvoiceByDateFromGivenPeriod(from, to)));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getInputVatRequest")
  @ResponsePayload
  public GetInputVatResponse getInputVat(@RequestPayload GetInputVatRequest request)
      throws Exception {
    GetInputVatResponse response = new GetInputVatResponse();
    LocalDate from = xmlDataAdapter.convertToLocalDate(request.getDateFrom());
    LocalDate to = xmlDataAdapter.convertToLocalDate(request.getDateTo());

    response.setInputVat(invoiceBook.accept(invoiceIncome,
        invoiceBook.filterInvoiceByDateFromGivenPeriod(from, to)));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOutputVatRequest")
  @ResponsePayload
  public GetOutputVatResponse getOutputVat(@RequestPayload GetOutputVatRequest request)
      throws Exception {
    GetOutputVatResponse response = new GetOutputVatResponse();
    LocalDate from = xmlDataAdapter.convertToLocalDate(request.getDateFrom());
    LocalDate to = xmlDataAdapter.convertToLocalDate(request.getDateTo());

    response.setOutputVat(invoiceBook.accept(invoiceIncome,
        invoiceBook.filterInvoiceByDateFromGivenPeriod(from, to)));
    return response;
  }

}

