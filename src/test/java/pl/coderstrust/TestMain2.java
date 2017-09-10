package pl.coderstrust;

import pl.coderstrust.model.Invoice;

import java.util.List;

public class TestMain2 {

  public static void main(String[] args) {
    InvoicesGenerator invoicesGenerator = new InvoicesGenerator();
    List<Invoice> generatedList = invoicesGenerator.invoicesGeneratorWithRandomDateFrom2016(25);

    int stop = 0;
  }

}
