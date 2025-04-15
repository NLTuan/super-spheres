package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

/**
 * FXML controller  for the start page.
 *
 * @author Joseph Josue Forestal
 */
public class StartPageController {
    @FXML
    private Label labelTitle;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonExit;

    @FXML
    public void initialize() {
        buttonStart.setOnAction(event -> handleStartButton());
        buttonExit.setOnAction(event -> handleExitButton());

    }
    public  void handleStartButton(){
        MainApp.switchScene(MainApp.TEMPLATE_SELECTION_LAYOUT);
    }

    public void handleExitButton() {
        Platform.exit();
    }

}

