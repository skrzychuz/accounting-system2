package pl.coderstrust.rest;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.file.JsonHelper;
import pl.coderstrust.model.Buyer;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceBulider;
import pl.coderstrust.model.InvoiceBulider.BuyerBulider;


import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class WebMockTest {

  List<Invoice> listOfInvoice = new ArrayList<>();

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private JsonHelper jsonHelperMock;
  @MockBean
  private InFileDatabase inFileDatabase;

  @Test
  public void shouldAddInvoiceToDatabase() throws Exception {
    Invoice invoice1 = new InvoiceBulider()
        .withLocalDate(LocalDate.of(2016, 10, 15))
        .build();

    when(jsonHelperMock.convertInvoiceObjectToJsonAsString(invoice1)).thenReturn(
        "{\"id\":0,\"description\":null,\"amount\":null,\"vatRate\":null,\"vatAmount\":null,\""
            + "localDate\":[2016,8,20]}\n");

    this.mockMvc.perform(post("/invoices")
        .contentType(contentType)
        .content(jsonHelperMock.convertInvoiceObjectToJsonAsString(invoice1)))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void shouldGetInvoicesFromDatabase() throws Exception {

    List<Invoice> toTestList = new ArrayList<>();
    Invoice invoice1 = new InvoiceBulider()
        .withBuyer(new BuyerBulider()
            .withName("stefan")
            .bulid())
        .build();
    Invoice invoice2 = new InvoiceBulider()
        .withBuyer(new BuyerBulider()
            .withName("marian")
            .bulid())
        .build();

    toTestList.add(invoice1);
    toTestList.add(invoice2);

    when(inFileDatabase.getInvoicesUnsorted()).thenReturn(toTestList);

    this.mockMvc.perform(get("/invoices"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].buyer.name", is("stefan")))
        .andExpect(jsonPath("$[1].buyer.name", is("marian")));


  }

  @Test
  public void shouldGetSingleInvoiceFromDatabase() throws Exception {

    List<Invoice> toTestList = new ArrayList<>();
    Invoice invoice1 = new InvoiceBulider()
        .withBuyer(new BuyerBulider()
            .withName("Mietek")
            .bulid())
        .build();

    invoice1.setId(5);
    Invoice invoice2 = new InvoiceBulider().build();

    invoice2.setId(6);

    toTestList.add(invoice1);
    toTestList.add(invoice2);

    when(inFileDatabase.getInvoicesUnsorted()).thenReturn(toTestList);

    this.mockMvc.perform(get("/invoices/" + 5))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.id", is(5)))
        .andExpect(jsonPath("$.buyer.name", is("Mietek")));

  }
}

