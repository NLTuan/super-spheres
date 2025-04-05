package edu.vanier.template.controllers;

import edu.vanier.template.helpers.BuildInBodies;
import edu.vanier.template.math.Vector3D;
import edu.vanier.template.sim.Planet;
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
    private Button starButton;
    @FXML
    private Button planetButton;
    @FXML
    private boolean isPlanet;
    private SimulationMainPageController simulationController;


    @FXML
    public void initialize() {
        initializeComboBox();
        handleCreateButton();
        handleStarButtonAction();
        handlePlanetButtonAction();
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
            try {
                double mass = Double.parseDouble(massTextField.getText());
                double radius = Double.parseDouble(radiusTextField.getText());
                double velocity = Double.parseDouble(velocityTextField.getText());
                String bodyTexture = bodyTextureComboBox.getValue();

                Planet planet = new Planet(new Vector3D(0, 0, 0), new Vector3D(0, 0, 0), mass, radius);
                BuildInBodies buildInBodies = new BuildInBodies(planet);
                buildInBodies.applyTextures(bodyTexture);
                simulationController.getTilePanePlanets().getChildren().add(planet);


                createButton.getScene().getWindow().hide();

            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numeric values for mass, radius, and velocity.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void handleStarButtonAction() {
       starButton.setOnAction(event -> {
           bodyTextureComboBox.getItems().clear();
           bodyTextureComboBox.getItems().addAll("Sun");
           bodyTextureComboBox.setValue("Sun");

           planetButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
           starButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

           isPlanet = false;
       });
    }

    private void handlePlanetButtonAction() {
        planetButton.setOnAction(event -> {
            isPlanet = true;
            bodyTextureComboBox.getItems().clear();
            bodyTextureComboBox.getItems().addAll("Earth", "Mars", "Jupiter", "Uranus", "Mercury");
            bodyTextureComboBox.setValue("Earth");

            starButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            planetButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

        });
    }

    public void setSimulationController(SimulationMainPageController simulationController) {
        this.simulationController = simulationController;
    }

}