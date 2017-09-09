package pl.coderstrust.database.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.MyComparator;
import pl.coderstrust.model.Invoice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InFileDatabase implements Database {

  ObjectMapper mapper = new ObjectMapper()
      .registerModule(new ParameterNamesModule())
      .registerModule(new Jdk8Module())
      .registerModule(new JavaTimeModule());


  @Override
  public void saveInvoice(Invoice invoice) throws IOException {

    JsonAdapter jsonAdapter = new JsonAdapter();
    String jsonInString = mapper.writeValueAsString(invoice);
    jsonAdapter.saveStringToFile(jsonInString);
  }

  @Override
  public List<Invoice> getInvoices() throws Exception {
    JsonAdapter jsonAdapter = new JsonAdapter();
    return jsonAdapter.readStringFromFile(mapper, new ArrayList<>());
  }

  @Override
  public List<Invoice> getListOfInvoicesFromPeriodTime(LocalDate fromDate, LocalDate toDate)
      throws Exception {

    List<Invoice> newlist = getInvoices();
    List<Invoice> fromToList = new ArrayList<>();
    for (Invoice invoice : newlist) {
      if (invoice.getLocalDate().isAfter(fromDate) && invoice.getLocalDate().isBefore(toDate)) {
        fromToList.add(invoice);
      }
    }
    return fromToList;
  }


  @Override
  public List<Invoice> sortingList(List<Invoice> listToSort) {
    Collections.sort(listToSort, new MyComparator());

    return listToSort;
  }

  @Override
  public void clearDatabase() {
  }

}
