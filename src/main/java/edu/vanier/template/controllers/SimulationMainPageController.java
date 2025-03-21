package edu.vanier.template.controllers;

import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
        private  VBox vboxPlanetStatistic;
            //Components(Essentials):
            @FXML
            private Label labelStatisticPositionValue;
            @FXML
            private Label labelStatisticVelocityValue;
            @FXML
            private Label labelStatisticPlanetName;





    //Subscene simulation
    @FXML
    private SubScene subSceneSimulation;


    //Non fxml field members:
    private Group groupRootNode = new Group();
    private Camera camera = new PerspectiveCamera();


    public  void  initializeBinding(){
        if(this.subSceneSimulation == null || this.vboxMainRootNode == null){return;}
        vboxMainRootNode.sceneProperty().addListener((obs, oldScene, newScene)->{
            this.subSceneSimulation.widthProperty().bind(newScene == null ? oldScene.widthProperty() : newScene.widthProperty());
            this.subSceneSimulation.heightProperty().bind(newScene == null ? oldScene.heightProperty(): newScene.heightProperty());
            this.anchorPaneMainRootNode.setPrefWidth(newScene == null ? oldScene.getWidth() :  newScene.getWidth());
            this.anchorPaneMainRootNode.setPrefHeight(newScene == null ? oldScene.getHeight() : newScene.getHeight());
            Scene scene = vboxMainRootNode.getScene();
           if (newScene != null) {
               vboxAddPlanetButton.layoutXProperty().bind(scene.widthProperty().subtract(170));

               vboxAddPlanetButton.layoutYProperty().bind(scene.heightProperty().subtract(180));
           }

        });
    }

    public  void setSubSceneSimulation(){
        this.subSceneSimulation.setFill(Color.BLACK);
        this.subSceneSimulation.setCamera(this.camera);
        this.subSceneSimulation.getRoot().setStyle("-fx-background-color: transparent");
    }
    @FXML
    public  void initialize(){
        initializeBinding();
        setSubSceneSimulation();
    }

}
