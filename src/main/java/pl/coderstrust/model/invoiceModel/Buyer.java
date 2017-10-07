package pl.coderstrust.model.invoiceModel;

public class Buyer {

  private String name;
  private String taxpayerIdentificationNumber;

  public Buyer() {
  }

  public Buyer(String name, String taxpayerIdentificationNumber) {
    this.name = name;
    this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTaxpayerIdentificationNumber() {
    return taxpayerIdentificationNumber;
  }

  public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber) {
    this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
  }
}
