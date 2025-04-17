package edu.vanier.template.controllers;

import edu.vanier.template.helpers.BuildInBodies;
import edu.vanier.template.helpers.RotationClass;
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
    private RotationClass rotationClassForBody;
    private double rotationRate = 26;



    @FXML
    public void initialize() {
        rotationClassForBody = new RotationClass();
        rotationClassForBody.addBody(textureBodySphere, rotationRate);

        initializeComboBox();
        handleCreateButton();
        handleStarButtonAction();
        handlePlanetButtonAction();

        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (isPlanet) {
                textureBodySphere.setRadius(newValue.doubleValue()*0.4);
            } else {
                textureBodySphere.setRadius(newValue.doubleValue()*0.2);
            }

        });

        radiusSlider.setValue(300);

        rotationSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            rotationClassForBody = new RotationClass();
            rotationRate = newValue.doubleValue() * 26;
            rotationClassForBody.addBody(textureBodySphere, rotationRate);
        });
        rotationSlider.setValue(1);
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
                // example of what to do..
                BuildInBodies.applyTextures(textureBodySphere,"Mercury");
                break;
            case "Saturn":
                BuildInBodies.applyTextures(textureBodySphere,"Saturn");
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

                    rotationClassForBody.addBody(body, rotationRate);
                } else {
                    body = new Star(new Vector3D(0, 0, 0), new Vector3D(0, 0, 0), mass, radius);
                    rotationClassForBody.addBody(body, rotationRate);
                }
                BuildInBodies buildInBodies = new BuildInBodies(body);
                buildInBodies.applyTextures(bodyTexture);
                simulationController.getTilePanePlanets().getChildren().add(body);
                simulationController.unPauseSimulation();

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

           radiusSlider.setMin(800);
           radiusSlider.setMax(1200);
           massSlider.setMin(1500);
           massSlider.setMax(3000);
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

            radiusSlider.setMin(10);
            radiusSlider.setMax(600);
            massSlider.setMin(10);
            massSlider.setMax(1500);
            radiusSlider.setValue(300);
        });
    }

    public void setSimulationController(SimulationMainPageController simulationController) {
        this.simulationController = simulationController;
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