package pl.coderstrust.rest;


import static javax.management.Query.value;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.ResultMatcher;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Invoice.Builder;
import pl.coderstrust.model.InvoiceBook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class WebMockTest {


  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private InFileDatabase inFileDatabase;

  @Test
  public void greetingShouldReturnMessageFromService() throws Exception {

    List<Invoice> toTestList = new ArrayList<>();
    Invoice invoice1 = new Builder()
        .withDescription("asdf")
        .build();
    Invoice invoice2 = new Builder()
        .withDescription("yoyoyo")
        .build();

    toTestList.add(invoice1);
    toTestList.add(invoice2);

    when(inFileDatabase.getInvoicesUnsorted()).thenReturn(toTestList);

    this.mockMvc.perform(get("/invoices"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));

  }

//  @Test
//  public void testListPeopleInGroup() throws Exception {
//    List<Invoice> toTestList = new ArrayList<>();
//    Invoice invoice1 = new Builder()
//        .withDescription("asdf")
//        .build();
//    Invoice invoice2 = new Builder()
//        .withDescription("yoyoyo")
//        .build();
//
//    toTestList.add(invoice1);
//    toTestList.add(invoice2);
//
//    when(service.getInvoicesUnsorted()).thenReturn(toTestList);
//
//    this.mockMvc.perform(get("/invoices"))
//        .andExpect(status().isOk())
//        .andExpect((model().attribute()))
//
//
//    List<Person> expectedPeople = asList(new Person());
//    when(mockPeopleService.listPeople("someGroup")).thenReturn(expectedPeople);
//    mockMvc.perform(get("/people/someGroup"))
//        .andExpect(status().isOk())
//        .andExpect(model().attribute("people", expectedPeople))
//        .andExpect(view().name("peopleList"));
//
//  }


}

//  @Test
//  public void greetingShouldReturnMessageFromService() throws Exception {
//    List<Invoice> toTestList = new ArrayList<>();
//    Invoice invoice1 = new Builder()
//        .withDescription("asdf")
//        .build();
//    Invoice invoice2 = new Builder()
//        .withDescription("yoyoyo")
//        .build();
//
//    toTestList.add(invoice1);
//    toTestList.add(invoice2);
//
//    when(service.getInvoices()).thenReturn(toTestList);
//
//    this.mockMvc.perform(get("/invoices"))
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$", hasSize(2)));
//
//
//  }