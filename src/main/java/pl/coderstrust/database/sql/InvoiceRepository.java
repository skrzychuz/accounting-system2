package pl.coderstrust.database.sql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderstrust.model.invoiceModel.Invoice;

@Transactional
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {

  //List<Invoice> findByLocalDateBetween(LocalDate fromDate, LocalDate toDate);
//  @Query("select i from Invoice i where i.localDate >= :from and i.localDate <= :to ")
//  List<Invoice> findByLocalDateBetween(@Param("from") LocalDate from, @Param("to") LocalDate to);


}



