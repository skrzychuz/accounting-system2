package pl.coderstrust.database.sql;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.invoiceModel.Entry;
import pl.coderstrust.model.invoiceModel.Invoice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
@ConditionalOnProperty(name = "pl.coderstrust.database", havingValue = "inSqlDatabaseEntityManager")
public class InSqlDatabaseEntityManager implements Database {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void saveInvoice(Invoice invoice) {
    for (Entry entry : invoice.getEntries()) {
      entry.setInvoice(invoice);
    }
    entityManager.persist(invoice);
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
    updateAllIdInInvoice(id, invoice);
    entityManager.merge(invoice);
  }

  private void updateAllIdInInvoice(int id, Invoice invoice) {
    invoice.setId(id);
    invoice.getSeller().setId(id);
    invoice.getBuyer().setId(id);
  }
}
