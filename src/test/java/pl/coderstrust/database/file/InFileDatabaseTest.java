package pl.coderstrust.database.file;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.DatabaseTestAbstract;

public class InFileDatabaseTest extends DatabaseTestAbstract {

  @Override
  protected Database getDatabase() {
    return new InFileDatabase();
  }
}