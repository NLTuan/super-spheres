package edu.vanier.template.controllers;

import edu.vanier.template.helpers.BuildInBodies;
import edu.vanier.template.math.Vector3D;
import edu.vanier.template.sim.Body;
import edu.vanier.template.sim.Planet;
import edu.vanier.template.sim.Star;
import edu.vanier.template.ui.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
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
    private AnchorPane rootAnchorPane;
    @FXML
    private HBox hBox;
    @FXML
    private ComboBox<String> bodyTextureComboBox;
    @FXML
    private Circle bodyTextureCircle;

    @FXML
    private Button button;
    @FXML
    private Button starButton;
    @FXML
    private Button planetButton;
    @FXML
    private boolean isPlanet;
    private SimulationMainPageController simulationController;
    @FXML
    private Slider velocitySlider;
    @FXML
    private Slider radiusSlider;
    @FXML
    private Slider massSlider;


    @FXML
    public void initialize() {
        initializeComboBox();
        handleCreateButton();
        handleStarButtonAction();
        handlePlanetButtonAction();
        initializeBinding();
    }

    public void initializeComboBox() {
        bodyTextureComboBox.getItems().addAll("Earth", "Mars", "Jupiter", "Uranus", "Mercury");
        bodyTextureComboBox.setValue("Earth");
        bodyTextureCircle.setFill(new ImagePattern(new Image("/fxml/BuildInBodiesImages/EarthImage.jpeg")));

        bodyTextureComboBox.setOnAction(event -> {
            String selected = bodyTextureComboBox.getValue();
            updateImageView(selected);
        });
    }


    public void updateImageView(String selected) {
        switch (selected) {
            case "Earth":
                bodyTextureCircle.setFill(new ImagePattern(new Image("/fxml/BuildInBodiesImages/EarthImage.jpeg")));
                break;
            case "Mars":
                bodyTextureCircle.setFill(new ImagePattern(new Image("/fxml/BuildInBodiesImages/MarsImage.png")));
                break;
            case "Jupiter":
                bodyTextureCircle.setFill(new ImagePattern(new Image("/fxml/BuildInBodiesImages/JupiterImage.jpg")));
                break;
            case "Uranus":
                bodyTextureCircle.setFill(new ImagePattern(new Image("/fxml/BuildInBodiesImages/UranusImage.png")));
                break;
            case "Venus":
                bodyTextureCircle.setFill(new ImagePattern(new Image("/fxml/BuildInBodiesImages/VenusImage.png")));
                break;
            case "Sun":
                bodyTextureCircle.setFill(new ImagePattern(new Image("/fxml/BuildInBodiesImages/SolarImage.jpg")));
                break;
            case "Mercury":
                bodyTextureCircle.setFill(new ImagePattern(new Image("/fxml/BuildInBodiesImages/MercuryImage.png")));
                break;
            default:
                System.out.println("Unknown planet selection: " + selected);
                break;
        }
    }


    @FXML
    private void handleCreateButton() {
        button.setOnAction(event -> {
                double mass = massSlider.getValue();
                double radius = radiusSlider.getValue();
                double velocity = velocitySlider.getValue();
                String bodyTexture = bodyTextureComboBox.getValue();

                Body body;
                if (isPlanet) {
                    body = new Planet(new Vector3D(0, 0, 0), new Vector3D(0, 0, 0), mass, radius);
                } else {
                    body = new Star(new Vector3D(0, 0, 0), new Vector3D(0, 0, 0), mass, radius);
                }
                BuildInBodies buildInBodies = new BuildInBodies(body);
                buildInBodies.applyTextures(bodyTexture);
                simulationController.getTilePanePlanets().getChildren().add(body);

                button.getScene().getWindow().hide();
            System.out.println("hi");
        });
    }

    private void handleStarButtonAction() {
       starButton.setOnAction(event -> {
           bodyTextureComboBox.getItems().clear();
           bodyTextureComboBox.getItems().addAll("Sun");
           bodyTextureComboBox.setValue("Sun");

           planetButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
           starButton.setStyle("-fx-background-color: #262626; -fx-text-fill: white;");

           isPlanet = false;
           bodyTextureCircle.radiusProperty().bind(rootAnchorPane.widthProperty().multiply(0.18));

       });
    }

    private void handlePlanetButtonAction() {
        planetButton.setOnAction(event -> {

            bodyTextureComboBox.getItems().clear();
            bodyTextureComboBox.getItems().addAll("Earth", "Mars", "Jupiter", "Uranus", "Mercury");
            bodyTextureComboBox.setValue("Earth");

            starButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
            planetButton.setStyle("-fx-background-color: #262626; -fx-text-fill: white;");

            isPlanet = true;
            bodyTextureCircle.radiusProperty().bind(rootAnchorPane.widthProperty().multiply(0.13));

        });
    }

    public void setSimulationController(SimulationMainPageController simulationController) {
        this.simulationController = simulationController;
    }

    public void initializeBinding() {
        bodyTextureCircle.radiusProperty().bind(rootAnchorPane.widthProperty().multiply(0.15));
    }

}