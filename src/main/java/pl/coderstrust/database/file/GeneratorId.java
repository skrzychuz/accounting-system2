package pl.coderstrust.database.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
@Service
public class GeneratorId {

  private final File idFile;
  private final FileProcessor fileProcessor;


  /**
   * Constructor.
   */
  @Autowired
  public GeneratorId(FileProcessor fileProcessor) {

    this.fileProcessor = fileProcessor;
    this.idFile = new File("database\\uniqId.json");
  }

  public int generateNewId() {
    List<String> listOfId = fileProcessor.readFromFile(idFile);
    int id = Integer.parseInt(listOfId.get(listOfId.size() - 1));
    id++;
    fileProcessor.saveToFile(String.valueOf(id), idFile);
    return id;
  }
}