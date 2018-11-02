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
    //Text fields is primarily for textFields to be checked
    textFields.add(name);
    textFields.add(userName);
    textFields.add(password);
    textFields.add(confirmPassword);
    textFields.add(teamName);

    menuButton.setText("Spectator");
    accountType = AccountType.SPECTATOR;
  }

  /***
   * This handles what happens when the submit button is clicked
   *
   * @param event
   */
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

  /***
   * This handles what happens when the back to sign in button is clicked
   *
   * @param event
   */
  @FXML
  protected void handleBackToSignInButtonAction(ActionEvent event) {
    try {
      //Go to sign in scene
      changeScene("Login");
    } catch (Exception exception) {
      System.out.println("Exception caught");
    }
  }

  /***
   * This handles what happens when the spectator menu item is clicked
   */
  @FXML
  protected void handleSpectatorMenuItem() {
    menuItemSelected("Spectator", AccountType.SPECTATOR, true);
  }

  /***
   * This handles what happens when the player menu item is clicked
   */
  @FXML
  protected void handlePlayerMenuItem() {
    menuItemSelected("Player", AccountType.PLAYER, true);
  }

  /***
   * This handles what happens when the manager menu item is clicked
   */
  @FXML
  protected void handleManagerMenuItem() {
    menuItemSelected("Manager", AccountType.MANAGER, false);
  }

  /***
   * This method sets the text of the menu button, the type of account to be created, and disables/
   * enables the team name text field
   *
   * @param menuButtonText the text that the menu button text will be set to
   * @param accountType the type of account to be created
   * @param teamNameDisabled whether or not the team name text field should be enabled
   */
  private void menuItemSelected(String menuButtonText, AccountType accountType,
      boolean teamNameDisabled) {
    //Change text of menu button
    menuButton.setText(menuButtonText);
    //Set accountType to manager
    this.accountType = accountType;
    //Enable team name textfield
    teamName.setDisable(teamNameDisabled);
  }

  /***
   * Checks every account in the account list to see if the current text in the username textfield
   * matches an already taken username
   *
   * @return false if the username is already taken, and true if the username is not taken
   */
  public boolean accountDoesNotExist() {
    //IF there are already accounts made
    if (Account.accounts.size() > 0) {
      //Check all accounts
      for (Account account : Account.accounts) {
        //IF there is an account with entered username
        if (account.getUsername().equals(userName.getText())) {
          return false;
        }
      }
    }

    return true;
  }

  /***
   * confirms that the reentered password matches the original password textfield
   *
   * @return true if the text in password and confirm password match, and false if they do not
   * match
   */
  public boolean confirmPasswords() {
    return password.getText().equals(confirmPassword.getText());
  }

  /***
   * This method creates an account and, if account type is MANAGER, a team using the text from
   * the text fields in this scene. Created teams and accounts are then added to their respective
   * lists
   */
  public void createAccount() {
    //Get text from text textFields and create a new account with them
    //Check account type
    switch (accountType) {
      case SPECTATOR:
        //Create account and add it to account list
        Account.accounts
            .add(new Account(name.getText(), userName.getText(), password.getText(), accountType));
        break;
      case PLAYER:
        //Create account and add it to account list
        Account.accounts
            .add(new Player(name.getText(), userName.getText(), password.getText(), accountType));
        break;
      case MANAGER:
        //Create team using the team name text field text
        Team team = new Team(teamName.getText());
        //Add team to team list
        Team.teams.add(team);
        //Create account and add it to account list
        Account.accounts
            .add(new Manager(name.getText(), userName.getText(), password.getText(), accountType,
                team));
        break;
    }
  }
}
