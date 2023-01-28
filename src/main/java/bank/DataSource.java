package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {

  public static Connection connect(){
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    try{
          connection = DriverManager.getConnection(db_file);
          System.out.println("We are connected.");
    } catch(SQLException e){
      e.printStackTrace();
    }
    return connection;
  } 
  
  public static Customer getCustomer(String username){
    String sql = "SELECT * FROM Customers WHERE Username = ?";
    Customer customer = null;
    try(Connection connection = connect(); 
        PreparedStatement statement = connection.prepareStatement(sql)){
          statement.setString(1, username);
          try(ResultSet resultSet = statement.executeQuery()){
              customer = new Customer(
                resultSet.getInt("ID"), 
                resultSet.getString("NAME"),
                resultSet.getString("USERNAME"),
                resultSet.getString("PASSWORD"),
                resultSet.getInt("ACCOUNT_ID"));
          }
    }catch(SQLException e){
      e.printStackTrace();
    }
    return customer;
  }

  public static Account getAccount(int accountId){
    String sql = "SELECT * FROM Accounts WHERE ID = ?";
    Account account = null;
    try(Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)){
          statement.setInt(1, accountId);

          try(ResultSet resultSet = statement.executeQuery()){
                account = new Account(
                  resultSet.getInt("ID"),
                  resultSet.getString("TYPE"),
                  resultSet.getDouble("BALANCE"));
              }
    }catch(SQLException e){
      e.printStackTrace();
    }

    return account;
  }

  public static void main(String[] args){
    Account account = getAccount(43267);
    System.out.println(account.getBalance());
  }
}
