package pl.coderstrust.database.file;

import org.codehaus.jackson.map.ObjectMapper;
import pl.coderstrust.model.Invoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


class JsonAdapter {

  void saveStringToFile(String string) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter("src\\main\\resources\\TestJson3.json", true))) {

      bufferedWriter.write(string);
      bufferedWriter.newLine();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  List<Invoice> readStringFromFile(ObjectMapper mapper, List<Invoice> invoices) throws Exception {

    try (BufferedReader bufferedWriter = new BufferedReader(
        new FileReader("src\\main\\resources\\TestJson3.json"))) {

      String lineInvoice;
      while ((lineInvoice = bufferedWriter.readLine()) != null) {

        Invoice invoice = mapper.readValue(lineInvoice, Invoice.class);
        invoices.add(invoice);

      }
    }
    return invoices;
  }
}
