package pl.coderstrust.database;

public class InMemoryDatabaseTestAbstract extends DatabaseTestAbstract {

  @Override
  protected Database provideDatabase() {
    return new InMemoryDatabase();
  }
}