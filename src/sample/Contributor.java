package sample;

public class Contributor extends Account {

  protected Team team;

  public Contributor() {
    super();
  }

  public Contributor(String name, String userName, String passWord, AccountType type, Team team) {
    super(name, userName, passWord, type);
    this.team = team;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }
}
