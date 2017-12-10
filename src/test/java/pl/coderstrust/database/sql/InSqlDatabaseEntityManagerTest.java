package pl.coderstrust.database.sql;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import pl.coderstrust.model.InvoiceBuilder;
import pl.coderstrust.model.InvoiceBuilder.BuyerBulider;
import pl.coderstrust.model.InvoiceBuilder.SellerBulider;
import pl.coderstrust.model.invoiceModel.Buyer;
import pl.coderstrust.model.invoiceModel.Invoice;
import pl.coderstrust.model.invoiceModel.Seller;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class InSqlDatabaseEntityManagerTest {

  @Autowired
  private InSqlDatabaseEntityManager inSqlDatabaseEntityManager;

  @Test
  public void shouldSaveToBase() {
    //given
    Invoice invoice1 = new InvoiceBuilder()
        .withBuyer(new BuyerBulider().withIdNumber("1").bulid())
        .withSeller(new SellerBulider().withIdNumber("1").bulid())
        .withLocalDate(LocalDate.of(2015, 12, 12))
        .build();

    //when
    inSqlDatabaseEntityManager.saveInvoice(invoice1);
    Invoice invoice2 = inSqlDatabaseEntityManager.getInvoices().get(0);

    //then
    assertEquals(LocalDate.of(2015, 12, 12), invoice2.getLocalDate());

  }
}