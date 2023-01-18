package bank;

import bank.exceptions.AmountException;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    this.id = id;
    this.type = type;
    this.balance = balance;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws AmountException {
    if (amount < 1) {
      throw new AmountException("The Minimum Deposit is 1.00");
    }
    this.balance += amount;
    DataSource.updateAccountBalance(id, balance);
  }

  public void withdraw(double amount) throws AmountException {
    if (amount < 1) {
      throw new AmountException("The WithDraw Amount Must be Greater than 0.00");
    }
    if (amount > getBalance()) {
      throw new AmountException(
          "You do not have sufficient funds for this withdrawal! your current balance is: " + getBalance());
    }
    this.balance -= amount;
    DataSource.updateAccountBalance(id, balance);
  }

  @Override
  public String toString() {
    return "Account [id=" + id + ", type=" + type + ", balance=" + balance + "]";
  }

}
