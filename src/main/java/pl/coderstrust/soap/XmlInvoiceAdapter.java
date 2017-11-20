package pl.coderstrust.soap;

import generatedFromXSD.ObjectFactory;
import generatedFromXSD.XInvoice;
import generatedFromXSD.XInvoice.XBuyer;
import generatedFromXSD.XInvoice.XEntreis;
import generatedFromXSD.XInvoice.XSeller;
import pl.coderstrust.model.invoiceModel.Buyer;
import pl.coderstrust.model.invoiceModel.Entry;
import pl.coderstrust.model.invoiceModel.Invoice;
import pl.coderstrust.model.invoiceModel.Seller;

import java.util.ArrayList;
import java.util.List;

public class XmlInvoiceAdapter {

  static ObjectFactory objectFactory = new ObjectFactory();

  public XInvoice toXMLInvoice(Invoice invoice) {

    XInvoice xInvoice = objectFactory.createXInvoice();
    XBuyer xBuyer = objectFactory.createXInvoiceXBuyer();
    XSeller xSeller = objectFactory.createXInvoiceXSeller();

    for (Entry e : invoice.getEntries()) {
      XEntreis xEntreis = objectFactory.createXInvoiceXEntreis();
      xEntreis.setAmount(e.getAmount());
      xEntreis.setDesc(e.getDescription());
      xEntreis.setVatAmount(e.getVatAmount());
      xEntreis.setVatRate(e.getVatRate());
      xEntreis.setId(e.getId());
      xInvoice.getXEntreis().add(xEntreis);
    }

    xBuyer.setXId(invoice.getBuyer().getId());
    xBuyer.setXName(invoice.getBuyer().getName());
    xBuyer.setXTaxIdentificationNumber(invoice.getBuyer().getTaxIdentificationNumber());
    xInvoice.setXBuyer(xBuyer);
    xSeller.setXId(invoice.getSeller().getId());
    xSeller.setXName(invoice.getSeller().getName());
    xSeller.setXTaxIdentificationNumber(invoice.getSeller().getTaxIdentificationNumber());
    xInvoice.setXSeller(xSeller);
    xInvoice.setXId(invoice.getId());
    xInvoice.setXAmount(invoice.getAmount());
    xInvoice.setXVatAmount(invoice.getVatAmount());

    return xInvoice;
  }

  public Invoice toInvoice(XInvoice xInvoice) {

    Invoice invoice = new Invoice();
    Buyer buyer = new Buyer();
    Seller seller = new Seller();
    List<Entry> entryList = new ArrayList<>();

    for (XEntreis e : xInvoice.getXEntreis()) {
      Entry entries = new Entry();
      entries.setAmount(e.getAmount());
      entries.setDescription(e.getDesc());
      entries.setVatAmount(e.getVatAmount());
      entries.setVatRate(e.getVatRate());
      entries.setId(e.getId());
      entryList.add(entries);
    }
    invoice.setEntries(entryList);
    buyer.setId(xInvoice.getXBuyer().getXId());
    buyer.setName(xInvoice.getXBuyer().getXName());
    buyer.setTaxIdentificationNumber(xInvoice.getXBuyer().getXTaxIdentificationNumber());
    invoice.setBuyer(buyer);

    seller.setId(xInvoice.getXSeller().getXId());
    seller.setName(xInvoice.getXSeller().getXName());
    seller.setTaxIdentificationNumber(xInvoice.getXSeller().getXTaxIdentificationNumber());
    invoice.setSeller(seller);

    invoice.setId(xInvoice.getXId());
    invoice.setAmount(xInvoice.getXAmount());
    invoice.setVatAmount(xInvoice.getXVatAmount());

    return invoice;
  }


}
