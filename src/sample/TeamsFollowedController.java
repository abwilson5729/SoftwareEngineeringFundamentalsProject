package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class TeamsFollowedController extends Controller{

  private ObservableList list = FXCollections.observableArrayList();

  @FXML
  private ListView<Team> listView;

  /***
   * Show list of teams that the current user follows
   */
  @FXML
  protected void initialize(){
    //Add all teams in current users following list to the observable list
    list.addAll(Account.currentUser.getTeamsFollowed());
    //Set items in list view to the items in the observable list
    listView.setItems(list);
  }
}
