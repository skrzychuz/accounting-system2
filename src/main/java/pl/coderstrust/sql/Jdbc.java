package pl.coderstrust.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {

  public static void main(String[] argv) throws SQLException {

    System.out.println("-------- MySQL JDBC Connection Testing ------------");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("Where is your MySQL JDBC Driver?");
      e.printStackTrace();
      return;
    }

    System.out.println("MySQL JDBC Driver Registered!");
    Connection connection = null;

    try {
      connection = DriverManager
          .getConnection("jdbc:mysql://localhost:3306?serverTimezone=UTC", "root", "root");

    } catch (SQLException e) {
      System.out.println("Connection Failed! Check output console");
      e.printStackTrace();
      return;
    }
    if (connection != null) {
      System.out.println("You made it, take control your database now!");
    } else {
      System.out.println("Failed to make connection!");
    }

    Statement stmt = null;
    String query = "select * from world.city limit 10;";
    try {
      stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      PreparedStatement preparedStatement = connection.prepareStatement("Insert");
      while (rs.next()) {
        String name = rs.getString("Name");

        System.out.println(name);
//        int supplierID = rs.getInt("SUP_ID");
//        float price = rs.getFloat("PRICE");
//        int sales = rs.getInt("SALES");
//        int total = rs.getInt("TOTAL");
//        System.out.println(coffeeName + "\t" + supplierID +
//            "\t" + price + "\t" + sales +
//            "\t" + total);
      }
    } catch (SQLException e) {
      //   JDBCTutorialUtilities.printSQLException(e);
    } finally {
      if (stmt != null) {
        stmt.close();
      }
    }


  }
}
