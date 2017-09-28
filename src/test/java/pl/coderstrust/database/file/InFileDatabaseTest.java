package pl.coderstrust.database.file;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import pl.coderstrust.database.Database;
import pl.coderstrust.database.DatabaseTestAbstract;

import java.io.File;

public class InFileDatabaseTest extends DatabaseTestAbstract {

  FileProcessor fileProcessorMock = mock(FileProcessor.class);
  JsonHelper jsonHelperMock = mock(JsonHelper.class);
  File fileForTests = new File("src\\test\\resources\\dataForTest.json");
  public String testPath = fileForTests.getPath();
  DatabaseFilesPaths databaseFilesPaths = new DatabaseFilesPaths();


  @Override
  protected Database getDatabase() {
    return new InFileDatabase(databaseFilesPaths.testDataBase, new JsonHelper(),
        new FileProcessor());

  }

  //  @After
  //  public void cleaner() throws IOException {
  //    FileWriter fileWriter = new FileWriter(testPath);
  //    fileWriter.write("");
  //    fileWriter.close();
}




