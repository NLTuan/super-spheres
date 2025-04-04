package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;

import java.util.Objects;

/**
 * FXML controller  for the start page.
 *
 * @author Fadi Rasmy
 */
public class CreatePlanetController {
    @FXML
    private TextField massTextField;
    @FXML
    private TextField radiusTextField;
    @FXML
    private TextField velocityTextField;
    @FXML
    private ComboBox<String> bodyTextureComboBox;
    @FXML
    private ImageView bodyTextureImageView;
    @FXML
    private Button createButton;

    @FXML
    public void initialize() {
        initializeComboBox();
        handleCreateButton();
    }

    public void initializeComboBox() {
        bodyTextureComboBox.getItems().addAll("Earth", "Mars", "Jupiter", "Uranus", "Mercury");
        bodyTextureComboBox.setValue("Earth");
        bodyTextureImageView.setImage(new Image("/fxml/BuildInBodiesImages/EarthImage.jpeg"));

        bodyTextureComboBox.setOnAction(event -> {
            String selected = bodyTextureComboBox.getValue();
            updateImageView(selected);
        });
    }

    public void updateImageView(String selected) {
        switch (selected) {
            case "Earth":
                bodyTextureImageView.setImage(new Image("/fxml/BuildInBodiesImages/EarthImage.jpeg"));
                break;
            case "Mars":
                bodyTextureImageView.setImage(new Image("/fxml/BuildInBodiesImages/MarsImage.png"));
                break;
            case "Jupiter":
                bodyTextureImageView.setImage(new Image("/fxml/BuildInBodiesImages/JupiterImage.jpg"));
                break;
            case "Uranus":
                bodyTextureImageView.setImage(new Image("/fxml/BuildInBodiesImages/UranusImage.png"));
                break;
            case "Venus":
                bodyTextureImageView.setImage(new Image("/fxml/BuildInBodiesImages/VenusImage.png"));
                break;
            case "Sun":
                bodyTextureImageView.setImage(new Image("/fxml/BuildInBodiesImages/SolarImage.jpg"));
                break;
            case "Mercury":
                bodyTextureImageView.setImage(new Image("/fxml/BuildInBodiesImages/MercuryImage.png"));
                break;
            default:
                System.out.println("Unknown planet selection: " + selected);
                break;
        }
    }

    @FXML
    private void handleCreateButton() {
        createButton.setOnAction(event -> {
            createButton.getScene().getWindow().hide();
        });
        String mass = massTextField.getText();
        String radius = radiusTextField.getText();
        String velocity = velocityTextField.getText();
        String bodyTexture = bodyTextureComboBox.getValue();
    }

}