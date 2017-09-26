package pl.coderstrust.database.file;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFilesPaths {

  String mainDataBase = "database\\data.json";
  String restDataBase = "database\\restDataBaseFile.json";
  String testDataBase = "src\\test\\resources\\dataForTest.json";

}
