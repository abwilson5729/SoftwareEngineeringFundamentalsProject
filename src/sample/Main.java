package sample;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  //The stage we will be showing our scenes in
  public static Stage stage;
  //Map that contains our scenes, using a string as a key
  public static Map<String, Scene> scenes = new HashMap<String, Scene>();

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocs/Login.fxml"));

    //Create login scene and put it into scenes map
    Scene loginScene = new Scene(root, 500, 275);
    scenes.put("Login", loginScene);

    root = FXMLLoader.load(getClass().getResource("FXMLDocs/CreateAccount.fxml"));

    //Create create account scene and put it into scenes map
    Scene createAccountScene = new Scene(root, 600, 375);
    scenes.put("CreateAccount", createAccountScene);

    primaryStage.setTitle("FXML Welcome");
    primaryStage.setScene(scenes.get("Login"));

    primaryStage.setResizable(false);

    stage = primaryStage;

    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
