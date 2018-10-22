package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomePage extends Controller {

  @FXML
  private Label userName;
  @FXML
  private Button customizationButton;
  @FXML
  private Button followingButton;
  @FXML
  private Button edit_joinTeam;
  @FXML
  private Button editEvent;
  @FXML
  private Button addEvent;

  @FXML
  protected void initialize() {
    //If user has yet to sign in
    if (Main.currentUser == null) {
      //Set username text as guest and disable the following buttons:
      userName.setText("Guest");
      customizationButton.setDisable(true);
      customizationButton.setVisible(false);

      followingButton.setDisable(true);
      followingButton.setVisible(false);

      edit_joinTeam.setDisable(true);
      edit_joinTeam.setVisible(false);

      editEvent.setDisable(true);
      editEvent.setVisible(false);

      addEvent.setDisable(true);
      addEvent.setVisible(false);
    } else {
      //If a user has signed in set username label to the username of the currently signed in
      //account
      userName.setText(Main.currentUser.getUserName());

      //Depending on the current user's account type disable the following buttons:
      switch (Main.currentUser.getAccountType()) {
        case SPECTATOR:
          followingButton.setDisable(true);
          followingButton.setVisible(false);

          edit_joinTeam.setDisable(true);
          edit_joinTeam.setVisible(false);

          editEvent.setDisable(true);
          editEvent.setVisible(false);

          addEvent.setDisable(true);
          addEvent.setVisible(false);
          break;
        case PLAYER:
          edit_joinTeam.setText("Join Team");

          editEvent.setDisable(true);
          editEvent.setVisible(false);

          addEvent.setDisable(true);
          addEvent.setVisible(false);
          break;
        case MANAGER:
          break;
      }
    }
  }

  //This handles what happens when the sign in button is clicked
  @FXML
  protected void handleLoginButtonAction(ActionEvent e) {
    try {
      //Go to login scene
      changeScene("Login");
    } catch (Exception exception) {
      System.out.println("Exception caught");
    }
  }
}
