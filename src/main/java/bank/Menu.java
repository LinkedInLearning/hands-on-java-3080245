package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
  
  private Scanner scanner;

  public static void main(String[] args){
    System.out.println("Welcome to Globe Bank International!");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if(customer != null){
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }

  private Customer authenticateUser(){
    System.out.println("Please enter your username");
    String username = scanner.next();

    System.out.println("Please enter your password");
    String password = scanner.next();

    Customer customer = null;
    try{
      customer = Authenticator.login(username, password);
    } catch(LoginException e){
      System.out.println("There was an error: " + e.getMessage());
    }

    return customer;    
  }

  private void showMenu(Customer customer, Account account) {

  }
}