package pl.coderstrust.database.file;

import org.codehaus.jackson.map.ObjectMapper;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InFileDatabase implements Database {

  @Override
  public void saveInvoice(Invoice invoice) throws IOException {

    JsonAdapter jsonAdapter = new JsonAdapter();
    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(invoice);
    jsonAdapter.saveStringToFile(jsonInString);
  }

  @Override
  public List<Invoice> getInvoices() throws Exception {
    JsonAdapter jsonAdapter = new JsonAdapter();

    return jsonAdapter.readStringFromFile(new ObjectMapper(), new ArrayList<>());
  }
}
