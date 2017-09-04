package pl.coderstrust.database.file;

import static org.junit.Assert.*;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.DatabaseTestAbstract;

public class InFileDatabaseTest extends DatabaseTestAbstract {

  @Override
  protected Database getDatabase() {
    return new InFileDatabase();
  }
}