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
  public static Map<String, String> scenes = new HashMap<>();
  //The current account logged in
  public static Account currentUser;

  @Override
  public void start(Stage primaryStage) throws Exception {
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

  public static void loadScene(Parent root) {
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
