package sample;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public abstract class Controller {

  protected ArrayList<Node> fields = new ArrayList<Node>();

  protected void ChangeScene(String sceneName) {
    //Set new scene using the key sceneName
    Main.stage.setScene(Main.scenes.get(sceneName));
    clearFields();
  }

  //Clear all text field and text nodes
  private void clearFields() {
    for (Node node : fields) {
      if (node instanceof TextField) {
        ((TextField) node).setText("");
      } else if (node instanceof Text) {
        ((Text) node).setText("");
      }
    }
  }

  //Check to see all text fields in scene are all filled
  protected boolean checkFields() {
    for (Node node : fields) {
      if (node instanceof TextField) {
        if (((TextField) node).getText().equals("")) {
          return false;
        }
      }
    }

    return true;
  }
}
