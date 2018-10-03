package sample;

import java.util.ArrayList;

public class Account {
  //This list contains all accounts created
  public static ArrayList<Account> accounts = new ArrayList<Account>();

  //An account consists of a name, user name, and password
  private String name;
  private String userName;
  private String passWord;

  public Account(String name, String userName, String passWord){
    this.name = name;
    this.userName = userName;
    this.passWord = passWord;
  }

  public String getName(){
    return name;
  }

  public String getUserName(){
    return userName;
  }

  public String getPassWord(){
    return passWord;
  }
}
