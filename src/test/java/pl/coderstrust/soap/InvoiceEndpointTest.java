package pl.coderstrust.soap;

import static org.junit.Assert.*;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.xml.transform.StringSource;

import java.io.IOException;

public class InvoiceEndpointTest {



  @Test
  public void shouldValidateXsdGetInvoiceResponse() throws IOException {}
//    //given
//    String filePathRequest = "src/test/resources/SoapXmlRequests/getInvoiceRequestId1.xml";
//    String filePathResponse = "src/test/resources/SoapXmlRequests/getInvoiceResponseId1.xml";
//    org.springframework.core.io.Resource xsdSchema = new ClassPathResource("Invoices.xsd");
//    MockWebServiceClient mockClient;
//
//
//    XmlFileReader fileReader = new XmlFileReader();
//    String getInvoiceStringRequest = fileReader.readFromFile(filePathRequest);
//    String getInvoiceStringResponse = fileReader.readFromFile(filePathResponse);
//
//    Source requestPayload = new StringSource(getInvoiceStringRequest);
//    Source responsePayload = new StringSource(getInvoiceStringResponse);
//    //when
//    mockClient.sendRequest(withPayload(requestPayload))
//        //then
//        .andExpect(noFault())
//        .andExpect(payload(responsePayload))
//        .andExpect(validPayload(xsdSchema));
//  }
//
//  private RequestCreator withPayload(Source requestPayload) {
//  }

  @Test
  public void getInvoiceById() throws Exception {
  }

  @Test
  public void getAllInvoices() throws Exception {
  }

  @Test
  public void addInvoice() throws Exception {
  }

  @Test
  public void deleteInvoice() throws Exception {
  }

  @Test
  public void updateInvoice() throws Exception {
  }

  @Test
  public void getIncome() throws Exception {
  }

  @Test
  public void getCost() throws Exception {
  }

  @Test
  public void getInputVat() throws Exception {
  }

  @Test
  public void getOutputVat() throws Exception {
  }

}