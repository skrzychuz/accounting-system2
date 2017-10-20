package pl.coderstrust.database.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class IdGenerator {

  private final File idFile;
  private final FileProcessor fileProcessor;

  /**
   * Constructor.
   */
  @Autowired
  public IdGenerator(FileProcessor fileProcessor) {

    this.fileProcessor = fileProcessor;
    this.idFile = new File("database\\uniqId.json");
  }

  public int generateNewId() {
    int id = Integer.parseInt(fileProcessor.readFromFile(idFile).get(0));
    id++;
    fileProcessor.saveIdToFile(String.valueOf(id), idFile);
    return id;
  }
}
