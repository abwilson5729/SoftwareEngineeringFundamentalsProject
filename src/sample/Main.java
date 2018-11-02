package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Account.AccountType;

public class Main extends Application {

  //The stage we will be showing our scenes in
  public static Stage stage;
  //Map that contains our scenes, using a string as a key
  public static Map<String, String> scenes = new HashMap<>();

  public File teamsFile = new File("teams");
  public File accountsFile = new File("accounts");

  @Override
  public void start(Stage primaryStage) throws Exception {
    if (!teamsFile.exists()) {
      teamsFile.createNewFile();
    } else {
      readTeamsFile();
    }

    if (!accountsFile.exists()) {
      accountsFile.createNewFile();
    } else {
      readAccountsFile();
    }

    //Put fxml urls into the scenes hash map with a key that correlates to their file name
    scenes.put("Login", "FXMLDocs/Login.fxml");
    scenes.put("CreateAccount", "FXMLDocs/CreateAccount.fxml");
    scenes.put("HomePage", "FXMLDocs/HomePage.fxml");

    //Set up home page scene
    Parent root = FXMLLoader.load(getClass().getResource(scenes.get("HomePage")));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.setTitle("Welcome");
    primaryStage.setResizable(false);

    primaryStage.show();

    //Set stage to primaryStage
    stage = primaryStage;
  }

  public void stop() throws IOException {
    ArrayList<String> fileLines = new ArrayList<>();
    if (Team.teams.size() > 0) {
      for (Team team : Team.teams) {
        fileLines.add(team.getName());
      }

      Path file = Paths.get("teams");
      Files.write(file, fileLines, Charset.forName("UTF-8"));

      fileLines.clear();
    }

    if (Account.accounts.size() > 0) {

      for (Account account : Account.accounts) {
        account.getLines(fileLines);
        fileLines.add(account.getUsername() + " user end");
      }

      Path file = Paths.get("accounts");
      Files.write(file, fileLines, Charset.forName("UTF-8"));
    }
  }

  public void readTeamsFile() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(teamsFile));
    String st;

    while ((st = br.readLine()) != null) {
      Team.teams.add(new Team(st));
    }
  }

  public void readAccountsFile() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(accountsFile));

    Account tempAccount = null;

    String st;

    while ((st = br.readLine()) != null) {
      //System.out.println(st);
      if (st.contains("Type:")) {
        String accountType = st.substring(st.indexOf(":") + 1);
        switch (accountType) {
          case "SPECTATOR":
            tempAccount = new Account();
            tempAccount.setType(AccountType.SPECTATOR);
            break;
          case "PLAYER":
            tempAccount = new Player();
            tempAccount.setType(AccountType.PLAYER);
            break;
          case "MANAGER":
            tempAccount = new Manager();
            tempAccount.setType(AccountType.MANAGER);
            break;
        }
      } else if (st.contains("User:")) {
        tempAccount.setUsername(st.substring(st.indexOf(":") + 1));
      } else if (st.contains("Name:")) {
        tempAccount.setName(st.substring(st.indexOf(":") + 1));
      } else if (st.contains("Password:")) {
        tempAccount.setPassword(st.substring(st.indexOf(":") + 1));
      } else if (st.contains("Team:")) {
        if (tempAccount instanceof Contributor) {
          ((Contributor) tempAccount)
              .setTeam(Team.findTeamByName(st.substring(st.indexOf(":") + 1)));
        }
      } else if (st.equals("Teams followed:")) {
        while (!(st = br.readLine()).equals("Teams followed end")) {
          tempAccount.followTeam(Team.findTeamByName(st));
        }
      } else if (st.contains("user end")) {
        Account.accounts.add(tempAccount);

        tempAccount = new Account("", "", "", AccountType.SPECTATOR);
      }
    }
  }

  public static void loadScene(Parent root) {
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
