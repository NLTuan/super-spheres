package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainApp;
import java.io.IOException;
import java.util.logging.Level;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML controller class for the secondary scene.
 *
 * @author frostybee
 */
public class SecondaryFXMLController {

    private final static Logger logger = LoggerFactory.getLogger(SecondaryFXMLController.class);
    @FXML
    BorderPane borderPane;
    @FXML
    Button btnSwitchScene;
    @FXML
    Button returnToStart;
    @FXML
    ImageView emptySystemImage;

    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");
        btnSwitchScene.setOnAction(this::loadPrimaryScene);
        returnToStart.setOnAction(this::returnToStart);
        emptySystemImage.fitWidthProperty().bind(borderPane.widthProperty().multiply(0.5));
    }

    private void loadPrimaryScene(Event e) {
        MainApp.switchScene(MainApp.MAIN_SCENE);
        logger.info("Loaded the primary scene...");
    }

    private void returnToStart(Event e) {
        MainApp.switchScene(MainApp.START_SCENE);
    }
}
