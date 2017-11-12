package pl.coderstrust.database.file;

public class newjava {

  long factorial(long number) {

    long result;

    if (number == 1) {
      return 1;
    } else {
      result = number * factorial(number - 1);
      return result;

    }
  }
}
