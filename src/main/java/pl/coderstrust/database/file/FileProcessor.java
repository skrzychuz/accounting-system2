package pl.coderstrust.database.file;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileProcessor {

  void saveToFile(String line, File file) {

    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(file, true))) {

      bufferedWriter.write(line);
      bufferedWriter.newLine();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  List<String> readFromFile(File file) {

    List<String> linesFromFile = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(
        new FileReader(file))) {

      String oneLine;
      while ((oneLine = bufferedReader.readLine()) != null) {
        linesFromFile.add(oneLine);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return linesFromFile;
  }

  /**
   * Clear Method.
   */
  void clearTheFile(File file) {

    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(file))) {

      bufferedWriter.write("");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void saveIdToFile(String line, File file) {

    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(file))) {

      bufferedWriter.write(line);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
