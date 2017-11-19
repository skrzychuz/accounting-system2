package pl.coderstrust.soap;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.ws.server.endpoint.annotation.Endpoint;
    import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
    import org.springframework.ws.server.endpoint.annotation.RequestPayload;
    import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

    import io.spring.guides.gs_producing_web_service.GetInovicesRequest;
    import io.spring.guides.gs_producing_web_service.GetInvoicesResponse;
    import pl.coderstrust.model.InvoiceBook;

@Endpoint
public class InvoiceEndpoint {
  private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

  private InvoiceBook invoiceBook;

  @Autowired
  public InvoiceEndpoint(InvoiceBook invoiceBook) {
    this.invoiceBook = invoiceBook;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getInvoicesRequest")
  @ResponsePayload
  public GetInvoicesResponse getCountry(@RequestPayload GetInovicesRequest request) {
    return new GetInvoicesResponse();
  }
}