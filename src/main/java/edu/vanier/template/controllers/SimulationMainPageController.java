package edu.vanier.template.controllers;

import edu.vanier.template.helpers.BuildInBodies;
import edu.vanier.template.math.Physics;
import edu.vanier.template.math.Vector3D;
import edu.vanier.template.sim.BodyHandler;
import edu.vanier.template.sim.CameraControlsHandler;
import edu.vanier.template.sim.Planet;
import java.util.Calendar;
import java.util.Date;

import edu.vanier.template.sim.Star;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class SimulationMainPageController {

    // All the main containers:
    @FXML
    private VBox vboxMainRootNode;
    @FXML
    private AnchorPane anchorPaneMainRootNode;

    //Containers with necessaryComponents:
    @FXML
    private VBox vboxAddPlanetButton;
    //Components:
    @FXML
    private Button buttonAddPlanet;

    @FXML
    private HBox hboxRootToolBar;
    // Components:
    @FXML
    private AnchorPane anchorPaneToolBarKit;
    @FXML
    private TitledPane tiltPanePlanets;
    @FXML
    private TilePane tilePanePlanets;
    @FXML
    private VBox vBoxCustomizeButton;
    @FXML
    private Button buttonCustomizePlanet;

    @FXML
    private VBox vboxCameraIcon;
    //Components(Essential):
    @FXML
    private Button buttonCamera;

    @FXML
    private VBox vboxPlanetStatistic;
    //Components(Essentials):
    @FXML
    private Label labelStatisticPositionValue;
    @FXML
    private Label labelStatisticVelocityValue;
    @FXML
    private Label labelStatisticPlanetName;
    @FXML
    private VBox vboxCameraControls;
    //Components(essentials):
    @FXML
    private Button buttonSetBackOrigin;
    @FXML
    private TextField textFieldCameraSpeed;

    //Subscene simulation
    @FXML
    private SubScene subSceneSimulation;

    //Non fxml field members:
    private Group groupRootNode = new Group();
    
    private PerspectiveCamera camera = new PerspectiveCamera(true);
    private CameraControlsHandler cameraControlsHandler;
    
    private AnimationTimer animationTimer;
    private long prevTime = System.nanoTime();

    private BodyHandler bodyHandler;

    double count = 1.0;
    double count2 = 1.0;
    public void handlerButtonAddPlanetEvent() {
        if (!vboxAddPlanetButton.isVisible()) {
            return;
        }
        // animations can be added here
        hboxRootToolBar.setVisible(true);
        vboxAddPlanetButton.setVisible(false);
        vboxAddPlanetButton.setManaged(false);
        this.tiltPanePlanets.setExpanded(true);

    }

    public void handlerTitlePaneEvent() {
        this.tiltPanePlanets.expandedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.2625));
                pauseTransition.setOnFinished(s -> {
                    tiltPanePlanets.setExpanded(false);
                    hboxRootToolBar.setVisible(false);
                    hboxRootToolBar.setManaged(false);
                    vboxAddPlanetButton.setVisible(true);
                    vboxAddPlanetButton.setManaged(true);
                });
                pauseTransition.play();

            }
        });
    }

    public void handlerCameraButtonEvent() {
        //animations logic to be added
        this.buttonCamera.setOnAction(e -> {
            if (!this.vboxCameraControls.isVisible()) {
                this.vboxCameraControls.setVisible(true);
                this.vboxCameraControls.setManaged(true);
            } else {
                this.vboxCameraControls.setVisible(false);
                this.vboxCameraControls.setManaged(false);
            }

        });
    }

    public void initializeBinding() {
        this.tiltPanePlanets.setExpanded(false);
        this.vboxCameraControls.setManaged(false);
        this.vboxCameraControls.setVisible(false);
        Sphere sphere = new Sphere(30);
        sphere.setMaterial(new PhongMaterial(Color.CORAL));
        this.tilePanePlanets.getChildren().add(sphere);

        if (this.subSceneSimulation == null || this.vboxMainRootNode == null) {
            return;
        }

        vboxMainRootNode.sceneProperty().addListener((obs, oldScene, newScene) -> {
            this.vboxMainRootNode.prefWidthProperty().bind(newScene == null ? oldScene.widthProperty() : newScene.widthProperty());
            this.vboxMainRootNode.prefHeightProperty().bind(newScene == null ? oldScene.heightProperty() : newScene.heightProperty());

            this.anchorPaneMainRootNode.prefWidthProperty().bind(newScene == null ? oldScene.widthProperty() : newScene.widthProperty());
            this.anchorPaneMainRootNode.prefHeightProperty().bind(newScene == null ? oldScene.heightProperty() : newScene.heightProperty());

            this.subSceneSimulation.widthProperty().bind(newScene == null ? oldScene.widthProperty() : newScene.widthProperty());
            this.subSceneSimulation.heightProperty().bind(newScene == null ? oldScene.heightProperty() : newScene.heightProperty());

            this.hboxRootToolBar.prefWidthProperty().bind(newScene == null ? oldScene.widthProperty() : newScene.widthProperty());

            this.anchorPaneToolBarKit.prefWidthProperty().bind((newScene == null ? oldScene.widthProperty() : newScene.widthProperty()));
            // this.anchorPaneToolBarKit.setStyle("-fx-border-color: blue");

            this.tiltPanePlanets.prefWidthProperty().bind(hboxRootToolBar.prefWidthProperty());
            this.tilePanePlanets.prefWidthProperty().bind((hboxRootToolBar.prefWidthProperty()));

        });

        vboxMainRootNode.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            vboxAddPlanetButton.setLayoutX(newWidth.doubleValue() - 150);

        });
        vboxMainRootNode.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            vboxAddPlanetButton.setLayoutY(newHeight.doubleValue() - 150);
            hboxRootToolBar.setLayoutY(newHeight.doubleValue() * 0.88);
        });

    }

    public void handleCamera() {
        Box box = new Box(25, 25, 20);
        box.setMaterial(new PhongMaterial(Color.RED));
        
        groupRootNode.getChildren().add(box);

        
        this.camera.setFarClip(10000000);
        this.camera.setNearClip(0.1);
        this.camera.setTranslateZ(-3000);
        this.camera.setTranslateX(this.subSceneSimulation.getWidth() / 2);
        this.camera.setTranslateY(this.subSceneSimulation.getHeight() / 2);
        
        
        

        // this.subSceneSimulation.setRoot(groupRootNode);
        this.subSceneSimulation.setRoot(groupRootNode);
        this.subSceneSimulation.getRoot().setTranslateX(this.subSceneSimulation.getWidth() / 2);
        this.subSceneSimulation.getRoot().setTranslateY(this.subSceneSimulation.getHeight() / 2);
        this.subSceneSimulation.getRoot().setTranslateZ(0);
        this.subSceneSimulation.setCamera(this.camera);
        cameraControlsHandler = new CameraControlsHandler(camera);

        this.subSceneSimulation.sceneProperty().addListener((obs, oldScene, newScene) -> {

            cameraControlsHandler.setMovementAllow(true);
            cameraControlsHandler.handleCamera(subSceneSimulation.getScene());
        });

    }

    public void setSubSceneSimulation() {
        this.subSceneSimulation.setFill(Color.BLACK);
        this.subSceneSimulation.setCamera(this.camera);
        this.subSceneSimulation.setDepthTest(DepthTest.ENABLE);
    }
    
    public void setupAnimationTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double dt = (now - prevTime) / 1E9 * 50;
                cameraControlsHandler.updateMovement(dt / 50);
                bodyHandler.update(dt);
                prevTime = now;
            }
        };
    }

    public void setupBodies(){


        bodyHandler = new BodyHandler();
        Planet planet = new Planet(new Vector3D(-650 , 0, .01), new Vector3D(0, 0, -12.28), 100.0, 10);

        Planet planet2 = new Planet(new Vector3D(0  , 0, 0), new Vector3D(0,0,0),10000, 170);

        Planet planet3 = new Planet(new Vector3D(0  , 0, 0), new Vector3D(0,0,0),150, 170);
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        Rotate yRotate2 = new Rotate(0, Rotate.Y_AXIS);

        planet.getTransforms().add(yRotate);
        planet2.getTransforms().add(yRotate2);
AnimationTimer animationTimer1 = new AnimationTimer() {
    @Override
    public void handle(long now) {
        count += 10;
        count2 += 0.05;
        yRotate.setAngle(count);
        yRotate2.setAngle(count2);

        if (count >= 360 ) count = 0;
    }
};
animationTimer1.start();


        BuildInBodies buildInBodies = new BuildInBodies(planet);
        BuildInBodies buildInBodies1 = new BuildInBodies(planet2);






        buildInBodies.applyTextures("Mercury");
        buildInBodies1.applyTextures("Sun");
      //  buildInBodies2.applyTextures("Sun");

        groupRootNode.getChildren().add(planet);
        groupRootNode.getChildren().add(planet2);
       // groupRootNode.getChildren().add(starSun);


       bodyHandler.add(planet);
        bodyHandler.add(planet2);
      //  bodyHandler.add(starSun);


    }

    @FXML
    public void initialize() {

        groupRootNode.setDepthTest(DepthTest.ENABLE);


        // Button events
        this.buttonAddPlanet.setOnAction(e -> {
            handlerButtonAddPlanetEvent();
        });
        
        handlerCameraButtonEvent();
        initializeBinding();

        setSubSceneSimulation();
        handlerTitlePaneEvent();

        handleCamera();

        setupBodies();
        setupAnimationTimer();
        animationTimer.start();
    }

}
