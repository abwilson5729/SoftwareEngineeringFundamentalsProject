package sample;

import java.io.IOException;
import java.util.ArrayList;

public class Manager extends Contributor {

  public Manager() {
    super();
  }

  public Manager(String name, String userName, String passWord, AccountType type, Team team) {
    super(name, userName, passWord, type, team);
  }

  public void getLines(ArrayList<String> list) throws IOException {
    super.getLines(list);
    list.add("Team:" + team.getName());
  }
}
