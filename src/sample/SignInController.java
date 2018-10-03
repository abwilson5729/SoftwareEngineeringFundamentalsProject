package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignInController extends Controller {

  @FXML
  //actionTarget is used for pop up messages to the user
  private Text actionTarget;
  @FXML
  private TextField userName;
  @FXML
  private PasswordField password;

  private String accountName;

  @FXML
  public void initialize() {
    //Add text field and text nodes to fields
    //This is primarily for fields to be checked and to be cleared when scenes are changed
    fields.add(actionTarget);
    fields.add(userName);
    fields.add(password);
  }

  //This handles what happens when the sign in button is clicked
  @FXML
  protected void handleSignInButtonAction(ActionEvent event) {
    accountName = "";

    //IF all text and password fields are filled
    if (checkFields()) {
      //IF there is an account with the entered username and password
      if (checkUserAndPassword()) {
        //Notify user whose account you're signing into
        actionTarget.setText("You're signing into " + accountName + "'s account");
      } else {
        //Notify user that they have entered an invalid username or password
        actionTarget.setText("Invalid username or password");
      }
    } else {
      //Notify user to fill all text and password fields
      actionTarget.setText("Please fill all fields");
    }
  }

  //This handles what happens when the create account button is clicked
  @FXML
  protected void handleCreateAccountButtonAction(ActionEvent event) {
    //Change current scene to CreateAccount
    ChangeScene("CreateAccount");
  }

  public boolean checkUserAndPassword() {
    //IF there are already accounts made
    if (Account.accounts.size() > 0) {
      //Check all accounts
      for (Account account : Account.accounts) {
        //IF there is an account with entered username and password
        if (account.getUserName().equals(userName.getText()) &&
            account.getPassWord().equals(password.getText())) {
          accountName = account.getName();
          return true;
        }
      }
    }

    return false;
  }

}
