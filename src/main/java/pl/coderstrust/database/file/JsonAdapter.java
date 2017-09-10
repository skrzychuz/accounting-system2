package pl.coderstrust.database.file;


import com.fasterxml.jackson.databind.ObjectMapper;
import pl.coderstrust.model.Invoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


class JsonAdapter {

  void saveStringToFile(String string) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter("src\\main\\resources\\TestJson100.json", true))) {

      bufferedWriter.write(string);
      bufferedWriter.newLine();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  List<Invoice> readStringFromFile(ObjectMapper mapper, List<Invoice> invoices) throws Exception {

    try (BufferedReader bufferedReader = new BufferedReader(
        new FileReader("src\\main\\resources\\TestJson100.json"))) {

      String lineInvoice;
      while ((lineInvoice = bufferedReader.readLine()) != null) {
        Invoice invoice1 = mapper.readValue(lineInvoice, Invoice.class);
        invoices.add(invoice1);

      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    Collections.sort(invoices, new Invoice());

    return invoices;
  }
}
