package pl.coderstrust.database.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Money;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
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

    ObjectMapper mapper = new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());

    return jsonAdapter.readStringFromFile(mapper, new ArrayList<>());
  }

  public static void main(String[] args) throws IOException {
    ObjectMapper mapper = new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());

    Invoice invoice = new Invoice(1, "abc", new Money(), LocalDate.now());

    String result = mapper.writeValueAsString(invoice);
    System.out.println(result);

    Invoice invoice2 = mapper.readValue(result, Invoice.class);
    System.out.println(invoice2.getLocalDate());

  }
}
