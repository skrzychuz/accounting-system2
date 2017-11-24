package pl.coderstrust.database.sql;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.OperationResult;
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
  public OperationResult deleteInvoice(int id) {
    try {
      entityManager.remove(
          entityManager.createQuery("from Invoice where id = :id").setParameter("id", id)
              .getSingleResult());
      return OperationResult.SUCCES;
    } catch (NoResultException e) {
      return OperationResult.FAILURE;
    }
  }

  @Override
  public OperationResult updateInvoice(int id, Invoice invoice) {

    if (getInvoices().stream()
        .filter(invoice44 -> invoice44.getId() == id)
        .findFirst()
        .orElse(null) != null) {
      updateAllIdInInvoice(id, invoice);
      entityManager.merge(invoice);
      return OperationResult.SUCCES;
    } else {
      return OperationResult.FAILURE;
    }
  }

  private void updateAllIdInInvoice(int id, Invoice invoice) {
    invoice.setId(id);
    invoice.getSeller().setId(id);
    invoice.getBuyer().setId(id);
    for (int i = 0; i < invoice.getEntries().size(); i++) {
      invoice.getEntries().get(i).setId((
          getInvoices().stream()
              .filter(invoice44 -> invoice44.getId() == id)
              .findFirst()
              .orElse(null))
          .getEntries().get(i).getId());
      invoice.getEntries().get(i).setInvoice(invoice);
    }
  }
}
