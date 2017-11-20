package pl.coderstrust.soap;

import generatedFromXSD.AddInvoiceRequest;
import generatedFromXSD.AddInvoiceResponse;
import generatedFromXSD.GetAllInvoicesRequest;
import generatedFromXSD.GetAllInvoicesResponse;
import generatedFromXSD.ObjectFactory;
import generatedFromXSD.XInvoice;
import generatedFromXSD.XInvoice.XBuyer;
import generatedFromXSD.XInvoice.XEntreis;
import generatedFromXSD.XInvoice.XSeller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import generatedFromXSD.GetInovicesRequest;
import generatedFromXSD.GetInvoicesResponse;
import pl.coderstrust.model.InvoiceBook;
import pl.coderstrust.model.invoiceModel.Invoice;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class InvoiceEndpoint {

  private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
  private InvoiceBook invoiceBook;
  private XmlInvoiceAdapter xmlInvoiceAdapter = new XmlInvoiceAdapter();


  @Autowired
  public InvoiceEndpoint(InvoiceBook invoiceBook) {
    this.invoiceBook = invoiceBook;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getInovicesRequest")
  @ResponsePayload
  public GetInvoicesResponse getInvoiceById(@RequestPayload GetInovicesRequest request) {
    GetInvoicesResponse response = new GetInvoicesResponse();
    Invoice invoice = invoiceBook.getInvoices().get(request.getId() - 1);
    response.setInvoice(xmlInvoiceAdapter.toXMLInvoice(invoice));

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllInvoicesRequest")
  @ResponsePayload
  public GetAllInvoicesResponse getAllInvoices(@RequestPayload GetAllInvoicesRequest request) {
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
    invoice = xmlInvoiceAdapter.toInvoice(request.getInvoice());
    invoiceBook.addInvoices(invoice);

    response.setId(invoice.getId());

    return response;
  }

}

