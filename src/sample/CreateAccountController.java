package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Account.AccountType;

public class CreateAccountController extends Controller {

  @FXML
  //actionTarget is used for pop up messages to the user
  private Text actionTarget;
  @FXML
  private TextField name;
  @FXML
  private TextField userName;
  @FXML
  private PasswordField password;
  @FXML
  private PasswordField confirmPassword;
  @FXML
  private TextField teamName;
  @FXML
  private MenuButton menuButton;

  private AccountType accountType;

  @FXML
  public void initialize() {
    //Add text field nodes to textFields
    //This is primarily for textFields to be checked
    textFields.add(name);
    textFields.add(userName);
    textFields.add(password);
    textFields.add(confirmPassword);
    textFields.add(teamName);

    accountType = AccountType.SPECTATOR;
  }

  //This handles what happens when the submit button is clicked
  @FXML
  protected void handleSubmitButtonAction(ActionEvent event) {
    //IF all text and password textFields are filled
    if (checkFields()) {
      //IF account does not exist
      if (accountDoesNotExist()) {
        //IF password and confirm password match
        if (confirmPasswords()) {
          //Create new account
          createAccount();
          //Notify user account has been created
          actionTarget.setText("Account created for " + userName.getText());
        } else {
          //Notify user that password and confirm password do not match
          actionTarget.setText("Passwords do not match");
        }
      } else {
        //Notify user that username is already in use
        actionTarget.setText("Username is already in use");
      }
    } else {
      //Notify user to fill all text and password textFields
      actionTarget.setText("Please fill all textFields");
    }
  }

  //This handles what happens when the back to sign in button is clicked
  @FXML
  protected void handleBackToSignInButtonAction(ActionEvent event) {
    try {
      //Go to login scene
      changeScene("Login");
    } catch (Exception exception){
      System.out.println("Exception caught");
    }
  }

  @FXML
  protected  void handleSpectatorMenuItem(){
    //Change text of menu button
    menuButton.setText("Spectator");
    //Set accountType to spectator
    accountType = AccountType.SPECTATOR;
    //Keep team name textfield disabled
    teamName.setDisable(true);
  }

  @FXML
  protected  void handlePlayerMenuItem(){
    //Change text of menu button
    menuButton.setText("Player");
    //Set accountType to player
    accountType = AccountType.PLAYER;
    //Keep team name textfield disabled
    teamName.setDisable(true);
  }

  @FXML
  protected  void handleManagerMenuItem(){
    menuButton.setText("Manager");
    accountType = AccountType.MANAGER;
    //Enable team name textfield
    teamName.setDisable(false);
  }

  public boolean accountDoesNotExist() {
    //IF there are already accounts made
    if (Account.accounts.size() > 0) {
      //Check all accounts
      for (Account account : Account.accounts) {
        //IF there is an account with entered username
        if (account.getUserName().equals(userName.getText())) {
          return false;
        }
      }
    }

    return true;
  }

  public boolean confirmPasswords() {
    return password.getText().equals(confirmPassword.getText());
  }

  public void createAccount() {
    //Get text from text textFields and create a new account with them
    Account.accounts.add(new Account(name.getText(), userName.getText(), password.getText(), accountType));
    //System.out.println(Account.accounts.size());
  }
}
