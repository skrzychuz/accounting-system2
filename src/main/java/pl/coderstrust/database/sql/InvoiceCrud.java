package pl.coderstrust.database.sql;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.coderstrust.model.invoiceModel.Invoice;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface InvoiceCrud extends CrudRepository<Invoice, Integer> {

  //List<Invoice> findByLocalDateBetween(LocalDate fromDate, LocalDate toDate);
//  @Query("select i from Invoice i where i.localDate >= :from and i.localDate <= :to ")
//  List<Invoice> findByLocalDateBetween(@Param("from") LocalDate from, @Param("to") LocalDate to);


}



