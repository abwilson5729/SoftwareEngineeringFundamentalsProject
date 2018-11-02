package sample;

import java.util.ArrayList;

public class Team {

  public static ArrayList<Team> teams = new ArrayList<>();

  private ArrayList<Player> members = new ArrayList<>();
  private ArrayList<Player> joinRequests = new ArrayList<>();

  private Manager manager;
  private String name;

  public Team(String name) {
    this.name = name;
  }

  public Team(Manager manager, String name) {
    this.manager = manager;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setManager(Manager manager) {
    this.manager = manager;
  }

  public void addPlayer(Player playerToAdd) {
    members.add(playerToAdd);
  }

  public void removePlayer(Player playerToRemove) {
    members.remove(playerToRemove);
  }

  public static Team findTeamByName(String teamName) {
    for (Team team : teams) {
      if (team.getName().equals(teamName)) {
        return team;
      }
    }

    return null;
  }

  public String toString(){
    return name;
  }
}
