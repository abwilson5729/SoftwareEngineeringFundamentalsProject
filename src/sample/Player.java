package sample;

public class Player extends Contributor {

  public Player() {
    super();
  }

  public Player(String name, String userName, String passWord, AccountType type) {
    super(name, userName, passWord, type, null);
  }
}
