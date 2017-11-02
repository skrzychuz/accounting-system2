package pl.coderstrust.database.sql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderstrust.model.invoiceModel.Invoice;

@Transactional
public interface inSqlDatabase extends CrudRepository<Invoice, Integer> {



}
