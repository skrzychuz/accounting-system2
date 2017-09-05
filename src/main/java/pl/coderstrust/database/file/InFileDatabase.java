package pl.coderstrust.database.file;


import org.codehaus.jackson.map.ObjectMapper;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InFileDatabase implements Database {


  @Override
  public void saveInvoice(Invoice invoice) throws IOException {

    ObjectMapper mapper = new ObjectMapper();
    Invoice invoice1 = new Invoice();

    String jsonInString = mapper.writeValueAsString(invoice1);
    jsonInString += "\n";

    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter("src\\main\\resources\\TestJson3.json"))) {

      bufferedWriter.write(jsonInString);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Invoice> getInvoices() throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    List<Invoice> invoices = new ArrayList<>();

    Invoice invoice = mapper
        .readValue(new FileReader("src\\main\\resources\\TestJson3.json"), Invoice.class);

    invoices.add(invoice);

    return invoices;
  }
}
