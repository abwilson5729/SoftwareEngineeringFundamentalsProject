package sample;

import java.io.IOException;
import java.util.ArrayList;

public class Account {

  //Types of accounts
  public enum AccountType {
    SPECTATOR,
    PLAYER,
    MANAGER,
  }

  //This list contains all accounts created
  public static ArrayList<Account> accounts = new ArrayList<>();
  //This is for the currently signed in user
  public static Account currentUser;

  //An account consists of a list of followed teams, name, user name, and password
  private ArrayList<Team> teamsFollowed = new ArrayList<>();

  private AccountType type;
  private String name;
  private String username;
  private String password;

  public Account() {
    name = "";
    username = "";
    password = "";
    type = AccountType.SPECTATOR;
  }

  public Account(String name, String username, String password, AccountType type) {
    this.name = name;
    this.username = username;
    this.password = password;
    this.type = type;
  }

  /***
   * Getter method for name
   *
   * @return name attribute of an instance of account
   */
  public String getName() {
    return name;
  }

  /***
   * Getter method for username
   *
   * @return username attribute of an instance of account
   */
  public String getUsername() {
    return username;
  }

  /***
   * Getter method for password
   *
   * @return username attribute of an instance of account
   */
  public String getPassword() {
    return password;
  }

  /***
   * Getter method for type
   *
   * @return type attribute of an instance of account
   */
  public AccountType getAccountType() {
    return type;
  }

  public ArrayList<Team> getTeamsFollowed(){
    return teamsFollowed;
  }

  /***
   * Setter method for name
   *
   * @param name the name attribute will be set to this parameter
   */
  public void setName(String name) {
    this.name = name;
  }

  /***
   * Setter method for username
   *
   * @param username the username attribute will be set to this parameter
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /***
   * Setter method for password
   *
   * @param password the password attribute will be set to this parameter
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /***
   * Setter method for type
   *
   * @param type the type attribute will be set to this parameter
   */
  public void setType(AccountType type) {
    this.type = type;
  }

  /***
   * Adds specific team to the teams followed list
   *
   * @param teamToFollow the team that will be added to the teams followed list
   */
  public void followTeam(Team teamToFollow) {
    teamsFollowed.add(teamToFollow);
  }

  /***
   * Removes specific team from the teams followed list
   *
   * @param teamToUnfollow the team that will be removed from the teams followed list
   */
  public void unfollowTeam(Team teamToUnfollow) {
    teamsFollowed.remove(teamToUnfollow);
  }

  /***
   * Adds string objects of each attribute of an instance of account to an array list
   *
   * @param list the list that string objects will be added to
   * @throws IOException
   */
  public void getLines(ArrayList<String> list) throws IOException {
    list.add("Type:" + type.toString());
    list.add("User:" + username);
    list.add("Name:" + name);
    list.add("Password:" + password);

    if (teamsFollowed.size() > 0) {
      list.add("Teams followed:");
      for (Team team : teamsFollowed) {
        list.add(team.getName());
      }
      list.add("Teams followed end");
    }
  }
}
