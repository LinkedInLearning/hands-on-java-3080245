package bank;

public class Customer {
  private int id;
  private String name;
  private String username;
  private String password;
  private int accountId;
  private boolean authenticated;

  public Customer(int id, String name, String username, String password, int accountId) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.password = password;
    this.accountId = accountId;
    setAuthenticated(false);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public boolean isAuthenticated() {
    return authenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }

  @Override
  public String toString() {
    return "Customer [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password
        + ", accountId=" + accountId + "]";
  }

}
