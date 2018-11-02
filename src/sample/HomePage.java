package sample;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

  private Stage popUpStage;

  private Scene popUpScene;

  /***
   * This method initializes a pop up stage and enables/disables certain home page buttons depending
   * on the current user's account type
   */
  @FXML
  protected void initialize() {
    //Initialize pop up stage
    popUpStage = new Stage();
    popUpStage.initModality(Modality.APPLICATION_MODAL);

    //If user has yet to sign in
    if (Account.currentUser == null) {
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
      userName.setText(Account.currentUser.getUsername());

      //Depending on the current user's account type disable the following buttons:
      switch (Account.currentUser.getAccountType()) {
        case SPECTATOR:
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

  /***
   * This handles what happens when the sign in button is clicked
   *
   * @param event this parameter is not used
   */
  @FXML
  protected void handleLoginButtonAction(ActionEvent event) {
    try {
      //Go to login scene
      changeScene("Login");
    } catch (Exception e) {
      System.out.println("Exception caught");
    }
  }

  /***
   * This handles what happens when the following button is clicked
   *
   * @param event this parameter is not used
   * @throws IOException
   */
  @FXML
  protected void handleFollowingButtonAction(ActionEvent event) throws IOException {
    loadPopUpScene("FXMLDocs/TeamsFollowedList.fxml", "Following");
  }

  /***
   * This handles what happens when the following button is clicked
   *
   * @param event this parameter is not used
   * @throws IOException
   */
  @FXML
  protected void handleEditAndJoinTeamButtonAction(ActionEvent event) throws IOException {
    //Depending on the account type of the current user difference scenes will pop up
    switch (Account.currentUser.getAccountType()) {
      case PLAYER:
        loadPopUpScene("FXMLDocs/JoinTeam.fxml", "Enter Team Name");
        break;
      case MANAGER:
        String managerTeamName = "";

        if (Account.currentUser instanceof Manager){
          managerTeamName = ((Manager) Account.currentUser).getTeam().getName();
        }
        loadPopUpScene("FXMLDocs/EditTeamScreen.fxml", managerTeamName);
        break;
      default:
        break;
    }
  }

  /***
   * This method loads in a pop up scene
   *
   * @param fxmlURL the URL of the fxml file that will be used for the scene
   * @param sceneTitle title of the scene
   * @throws IOException
   */
  private void loadPopUpScene(String fxmlURL, String sceneTitle) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource(fxmlURL));

    popUpScene = new Scene(root);

    popUpStage.setScene(popUpScene);
    popUpStage.setTitle(sceneTitle);
    popUpStage.setResizable(false);

    popUpStage.show();
  }
}
