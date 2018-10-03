package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
  public void initialize() {
    //Add text field and text nodes to fields
    //This is primarily for fields to be checked and to be cleared when scenes are changed
    fields.add(actionTarget);
    fields.add(name);
    fields.add(userName);
    fields.add(password);
    fields.add(confirmPassword);

    System.out.println(fields.size());
  }

  //This handles what happens when the submit button is clicked
  @FXML
  protected void handleSubmitButtonAction(ActionEvent event) {
    //IF all text and password fields are filled
    if (checkFields()) {
      //IF account does not exist
      if (accountDoesNotExist()) {
        //IF password and confirm password match
        if (confirmPasswords()) {
          //Create new account
          createAccount();
          //Notify user account has been created
          actionTarget.setText("Account created");
        } else {
          //Notify user that password and confirm password do not match
          actionTarget.setText("Passwords do not match");
        }
      } else {
        //Notify user that username is already in use
        actionTarget.setText("Username is already in use");
      }
    } else {
      //Notify user to fill all text and password fields
      actionTarget.setText("Please fill all fields");
    }
  }

  //This handles what happens when the back to sign in button is clicked
  @FXML
  protected void handleBackToSignInButtonAction(ActionEvent event) {
    ChangeScene("Login");
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
    //Get text from text fields and create a new account with them
    Account.accounts.add(new Account(name.getText(), userName.getText(), password.getText()));
    //System.out.println(Account.accounts.size());
  }
}
