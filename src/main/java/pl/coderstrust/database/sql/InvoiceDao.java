package pl.coderstrust.database.sql;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.invoiceModel.Invoice;


@Repository
@Transactional
@ConditionalOnProperty(name = "pl.coderstrust.database", havingValue = "invoiceDao")
public class InvoiceDao implements Database {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void saveInvoice(Invoice invoice) {

    invoice.setEntries(invoice.getEntries());
    entityManager.persist(invoice);
    return;
  }

  @Override
  public List<Invoice> getInvoices() {
    return entityManager.createQuery("from Invoice", Invoice.class).getResultList();

  }

  @Override
  public List<Invoice> getListOfInvoicesFromGivenPeriod(LocalDate fromDate, LocalDate toDate) {
    return null;
  }

  @Override
  public int getNextInvoiceId() {
    return 0;
  }

  @Override
  public void deleteInvoice(int id) {
    entityManager.remove(
        entityManager.createQuery("from Invoice where id = :id").setParameter("id", id)
            .getSingleResult());

  }

  @Override
  public void updateInvoice(int id, Invoice invoice) {

  }
}
