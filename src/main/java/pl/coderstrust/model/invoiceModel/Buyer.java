package pl.coderstrust.model.invoiceModel;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Buyer {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @ApiModelProperty(hidden = true)
  private int id;
  private String name;
  private String taxIdentificationNumber;

  public Buyer() {
  }

  public Buyer(String name, String taxIdentificationNumber) {
    this.name = name;
    this.taxIdentificationNumber = taxIdentificationNumber;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTaxIdentificationNumber() {
    return taxIdentificationNumber;
  }

  public void setTaxIdentificationNumber(String taxIdentificationNumber) {
    this.taxIdentificationNumber = taxIdentificationNumber;
  }
}
