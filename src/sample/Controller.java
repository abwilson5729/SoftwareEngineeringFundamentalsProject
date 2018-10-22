package sample;

import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public abstract class Controller {

  //All text fields in scene to be checked
  protected ArrayList<TextField> textFields = new ArrayList<TextField>();

  protected void changeScene(String sceneName) throws Exception {
    //Load fxml file using the scene url in the scenes map in main using its key scene name
    Parent root = FXMLLoader.load(getClass().getResource(Main.scenes.get(sceneName)));
    //Load scene using root
    Main.loadScene(root);
  }

  //Check to see all text textFields in scene are all filled
  protected boolean checkFields() {
    for (TextField node : textFields) {
      //If a textfield node is empty and enabled
      if (node.getText().equals("") && !node.isDisabled()) {
        return false;
      }
    }

    return true;
  }
}
