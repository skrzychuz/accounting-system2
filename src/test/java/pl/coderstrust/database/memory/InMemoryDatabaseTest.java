package pl.coderstrust.database.memory;

import static org.junit.Assert.*;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.DatabaseTestAbstract;

public class InMemoryDatabaseTest extends DatabaseTestAbstract {

  @Override
  protected Database getDatabase() {
    return new InMemoryDatabase();
  }
}