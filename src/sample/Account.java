package sample;

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

  //An account consists of a list of followed teams, name, user name, and password
  private ArrayList<Team> teamsFollowed = new ArrayList<>();

  private AccountType type;
  private String name;
  private String userName;
  private String passWord;

  public Account(String name, String userName, String passWord, AccountType type) {
    this.name = name;
    this.userName = userName;
    this.passWord = passWord;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassWord() {
    return passWord;
  }

  public AccountType getAccountType() {
    return type;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public void setType(AccountType type) {
    this.type = type;
  }

  public void followTeam(Team teamToFollow) {
    boolean alreadyFollowsTeam = false;

    //For every team in teams followed
    for (Team team : teamsFollowed) {
      //If user already follows the team to be followed
      if (team == teamToFollow) {
        //Set alreadyFollowsTeam to true
        alreadyFollowsTeam = true;
      }
    }

    //If user doesn't already follow the team to be followed
    if (!alreadyFollowsTeam) {
      //Add team to list of followed teams
      teamsFollowed.add(teamToFollow);
    }
  }

  public void unfollowTeam(Team teamToUnfollow) {
    teamsFollowed.remove(teamToUnfollow);
  }
}
