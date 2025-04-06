package edu.vanier.template.controllers;

import edu.vanier.template.helpers.BuildInBodies;
import edu.vanier.template.math.Vector3D;
import edu.vanier.template.sim.Body;
import edu.vanier.template.sim.Planet;
import edu.vanier.template.sim.Star;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

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
    private Button button;
    @FXML
    private Button starButton;
    @FXML
    private Button planetButton;
    @FXML
    private boolean isPlanet;
    private SimulationMainPageController simulationController;
    @FXML
    private Slider rotationSlider;
    @FXML
    private Slider radiusSlider;
    @FXML
    private Slider massSlider;
    @FXML
    private Sphere textureBodySphere;


    @FXML
    public void initialize() {
        initializeComboBox();
        handleCreateButton();
        handleStarButtonAction();
        handlePlanetButtonAction();
//        initializeBinding();

        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            textureBodySphere.setRadius(newValue.doubleValue()*1.2);
        });

        radiusSlider.setValue(60);
    }

    public void initializeComboBox() {
        bodyTextureComboBox.getItems().addAll("Earth", "Mars", "Jupiter", "Uranus", "Mercury");
        bodyTextureComboBox.setValue("Earth");
        texturizeBody(new Image("/fxml/BuildInBodiesImages/EarthImage.jpeg"), Color.BLUE);
        bodyTextureComboBox.setOnAction(event -> {
            String selected = bodyTextureComboBox.getValue();
            updateImageView(selected);
        });
    }


    public void updateImageView(String selected) {
        switch (selected) {
            case "Earth":
                texturizeBody(new Image("/fxml/BuildInBodiesImages/EarthImage.jpeg"), Color.BLUE);
                break;
            case "Mars":
                texturizeBody(new Image("/fxml/BuildInBodiesImages/MarsImage.png"), Color.DARKRED);
                break;
            case "Jupiter":
                texturizeBody(new Image("/fxml/BuildInBodiesImages/JupiterImage.jpg"), Color.BURLYWOOD);
                break;
            case "Uranus":
                texturizeBody(new Image("/fxml/BuildInBodiesImages/UranusImage.png"), Color.LIGHTBLUE);
                break;
            case "Venus":
                texturizeBody(new Image("/fxml/BuildInBodiesImages/VenusImage.png"), Color.GOLDENROD);
                break;
            case "Sun":
                texturizeBody(new Image("/fxml/BuildInBodiesImages/SolarImage.jpg"), Color.GOLD);
                break;
            case "Mercury":
                texturizeBody(new Image("/fxml/BuildInBodiesImages/MercuryImage.png"), Color.DARKGRAY);
                break;
            default:
                break;
        }
    }


    @FXML
    private void handleCreateButton() {
        button.setOnAction(event -> {
                double mass = massSlider.getValue();
                double radius = radiusSlider.getValue();
                //double velocity = rotationSlider.getValue();
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

           radiusSlider.setMin(150);
           radiusSlider.setMax(200);
           massSlider.setMin(150);
           massSlider.setMax(200);
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

            radiusSlider.setMin(20);
            radiusSlider.setMax(100);
            massSlider.setMin(20);
            massSlider.setMax(100);
            radiusSlider.setValue(60);
        });
    }

    public void setSimulationController(SimulationMainPageController simulationController) {
        this.simulationController = simulationController;
    }

    public void initializeBinding() {
        textureBodySphere.radiusProperty().bind(rootAnchorPane.widthProperty().multiply(0.13));
    }

    public void texturizeBody(Image image , Color color){
        PhongMaterial phongMaterial = new PhongMaterial(color);
        phongMaterial.setBumpMap(image);
        phongMaterial.setDiffuseMap(image);
        phongMaterial.setSpecularMap(image);
        phongMaterial.setSelfIlluminationMap(image);
        this.textureBodySphere.setMaterial(phongMaterial);
    }


}