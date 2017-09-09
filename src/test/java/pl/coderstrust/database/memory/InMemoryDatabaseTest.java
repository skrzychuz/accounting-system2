package pl.coderstrust.database.memory;

import org.junit.Test;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.DatabaseTestAbstract;
import pl.coderstrust.model.Invoice;

import java.time.LocalDate;
import java.util.List;

public class InMemoryDatabaseTest extends DatabaseTestAbstract {

  @Override
  protected Database getDatabase() {
    return new InMemoryDatabase();
  }

}