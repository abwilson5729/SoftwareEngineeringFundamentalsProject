package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class CalendarController extends Controller {

    String eventName = "Basketball";
    private ObservableList list = FXCollections.observableArrayList();

    @FXML
    private Accordion accordion;

    @FXML
    private ScrollPane scrollPane;

    private ArrayList<TitledPane> titledPanes = new ArrayList<>();

    @FXML
    protected void initialize() {

        titledPanes.add(createNewPane(eventName));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));
        titledPanes.add(createNewPane("help"));

        accordion.getPanes().addAll(titledPanes);
        scrollPane.setContent(accordion);
    }
    public TitledPane createNewPane(String eventName){
        TitledPane titledpane = new TitledPane();
        titledpane.setText(eventName);
        titledpane.setPrefHeight(150.0);
        titledpane.setPrefWidth(600.0);
        return titledpane;
    }
}
