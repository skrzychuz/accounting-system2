package pl.coderstrust.database.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pl.coderstrust.model.invoiceModel.Invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonHelper {

  private
  ObjectMapper mapper;

  @Autowired
  public JsonHelper(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  List<Invoice> convertListOfStringsRepresentingInvoiceAsJsonToListOfInvoices(
      List<String> listToConvert) {

    List<Invoice> invoices = new ArrayList<>();
    for (String string : listToConvert) {
      Invoice invoice;
      try {
        invoice = mapper.readValue(string, Invoice.class);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      invoices.add(invoice);
    }
    return invoices;
  }

  public String convertInvoiceObjectToJsonAsString(Invoice invoice) {

    try {
      return mapper.writeValueAsString(invoice);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
