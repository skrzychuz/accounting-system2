package pl.coderstrust.database.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pl.coderstrust.model.Invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class JsonHelper {

  private MapperConfig mapperConfig = new MapperConfig();

  List<Invoice> jsonConvertFromStrinfToInvoice(List<String> listToConvert) {

    List<Invoice> invoices = new ArrayList<>();
    for (String string : listToConvert) {
      Invoice invoice;
      try {
        invoice = mapperConfig.mapper.readValue(string, Invoice.class);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      invoices.add(invoice);
    }
    return invoices;
  }

  String jsonConvertInvoiceToString(Invoice invoice) {

    try {
      return mapperConfig.mapper.writeValueAsString(invoice);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
