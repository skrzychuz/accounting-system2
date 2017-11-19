package pl.coderstrust.soap;

import io.spring.guides.gs_producing_web_service.ObjectFactory;
import io.spring.guides.gs_producing_web_service.XInvoice;
import io.spring.guides.gs_producing_web_service.XInvoice.XBuyer;
import io.spring.guides.gs_producing_web_service.XInvoice.XEntreis;
import io.spring.guides.gs_producing_web_service.XInvoice.XSeller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetInovicesRequest;
import io.spring.guides.gs_producing_web_service.GetInvoicesResponse;
import pl.coderstrust.model.InvoiceBook;
import pl.coderstrust.model.invoiceModel.Entry;
import pl.coderstrust.model.invoiceModel.Invoice;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class InvoiceEndpoint {

  private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
  ObjectFactory objectFactory = new ObjectFactory();
  private InvoiceBook invoiceBook;

  @Autowired
  public InvoiceEndpoint(InvoiceBook invoiceBook) {
    this.invoiceBook = invoiceBook;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getInovicesRequest")
  @ResponsePayload
  public GetInvoicesResponse getInvoicess(@RequestPayload GetInovicesRequest request) {
    GetInvoicesResponse response = new GetInvoicesResponse();
    Invoice invoice = invoiceBook.getInvoices().get(request.getId());

    XInvoice xInvoice = objectFactory.createXInvoice();
    XBuyer xBuyer = objectFactory.createXInvoiceXBuyer();
    XSeller xSeller = objectFactory.createXInvoiceXSeller();
    XEntreis xEntreis = objectFactory.createXInvoiceXEntreis();
    List<XEntreis> xEntriesList = new ArrayList<>();

    for (Entry e : invoice.getEntries()) {
      xEntreis.setAmount(e.getAmount());
      xEntreis.setDesc(e.getDescription());
      xEntreis.setVatAmount(e.getVatAmount());
      xEntreis.setVatRate(e.getVatRate());
      xInvoice.getXEntreis().add(xEntreis);
    }

    xInvoice.setXAmount(invoice.getAmount());
    xInvoice.setXVatAmount(invoice.getVatAmount());
    xBuyer.setXId(invoice.getBuyer().getId());
    xBuyer.setXName(invoice.getBuyer().getName());
    xBuyer.setXTaxIdentificationNumber(invoice.getBuyer().getTaxIdentificationNumber());
    xInvoice.setXBuyer(xBuyer);

    xSeller.setXId(invoice.getSeller().getId());
    xSeller.setXName(invoice.getSeller().getName());
    xSeller.setXTaxIdentificationNumber(invoice.getSeller().getTaxIdentificationNumber());
    xInvoice.setXSeller(xSeller);

    xInvoice.setXId(invoice.getId());
    response.setInvoice(xInvoice);

    return response;
  }
}