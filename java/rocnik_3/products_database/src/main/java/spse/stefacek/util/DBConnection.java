package spse.stefacek.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
  private static DBConnection instance;
  private Connection connection;
  private static final String URL = "jdbc:mysql://localhost:3306/product_shop";
  private static final String USER = "root";
  private static final String PASSWORD = "";

  private DBConnection() throws SQLException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (ClassNotFoundException e) {
      throw new SQLException("MySQL Driver not found", e);
    }
  }

  public static DBConnection getInstance() throws SQLException {
    if (instance == null || instance.connection.isClosed()) {
      instance = new DBConnection();
    }
    return instance;
  }

  public Connection getConnection() {
    return connection;
  }
}
