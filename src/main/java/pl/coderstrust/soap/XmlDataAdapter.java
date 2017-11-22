package pl.coderstrust.soap;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

@Service
public class XmlDataAdapter {


  public LocalDate convertToLocalDate(XMLGregorianCalendar xmlDate)
      throws DatatypeConfigurationException {
    return xmlDate.toGregorianCalendar().toZonedDateTime().toLocalDate();
  }


  public XMLGregorianCalendar convertToGregorianCalendar(LocalDate localDate)
      throws DatatypeConfigurationException {

    GregorianCalendar gcal = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
  }

}
