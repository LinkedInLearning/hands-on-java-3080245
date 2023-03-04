package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(db_file);
      System.out.println("We Are Connected");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return connection;
  }

  public static Customer getCustomer(String username) {
    String sql = "select * from customers where username = ?";
    Customer customer = null;
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, username);
      try (ResultSet resultset = statement.executeQuery()) {
        customer = new Customer(
            resultset.getInt("id"),
            resultset.getString("name"),
            resultset.getString("username"),
            resultset.getString("password"),
            resultset.getInt("account_id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customer;
  }

  public static Account getAccount(int accountId) {
    String sql = "select * from accounts where id = ?";
    Account account = null;
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, accountId);
      try (ResultSet resultset = statement.executeQuery()) {
        account = new Account(
            resultset.getInt("id"),
            resultset.getString("type"),
            resultset.getDouble("balance"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return account;
  }

  public static void updateAccountBalance(int accountId, double balance) {
    String sql = "update accounts set balance = ? where id = ?";
    try (
        Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql);) {
      statement.setDouble(1, balance);
      statement.setInt(2, accountId);
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Customer customer = getCustomer("twest8o@friendfeed.com");
    System.out.println(customer.getName());
    Account account = getAccount(customer.getAccountId());
    System.out.println(account.getBalance());
  }
}
