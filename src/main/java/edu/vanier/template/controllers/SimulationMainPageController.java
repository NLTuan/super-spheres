package edu.vanier.template.controllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

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


    public  void handlerButtonAddPlanetEvent(){
        if (!vboxAddPlanetButton.isVisible()){return;}
        // animations can be added here
        hboxRootToolBar.setVisible(true);
        vboxAddPlanetButton.setVisible(false);
        vboxAddPlanetButton.setManaged(false);
        this.tiltPanePlanets.setExpanded(true);

    }
    public void handlerTitlePaneEvent(){

        this.tiltPanePlanets.expandedProperty().addListener((obs,oldValue,newValue)->{

            if(!newValue){
                tiltPanePlanets.setExpanded(false);

                hboxRootToolBar.setVisible(false);
                hboxRootToolBar.setManaged(false);
                vboxAddPlanetButton.setVisible(true);
                vboxAddPlanetButton.setManaged(true);
            }
        });

    }

    public  void  initializeBinding(){
        this.tiltPanePlanets.setExpanded(false);
        Sphere sphere = new Sphere(30);
        sphere.setMaterial(new PhongMaterial(Color.CORAL));
        this.tilePanePlanets.getChildren().add(sphere);
        if(this.subSceneSimulation == null || this.vboxMainRootNode == null){return;}

        vboxMainRootNode.sceneProperty().addListener((obs, oldScene, newScene)->{
            this.vboxMainRootNode.prefWidthProperty().bind(newScene == null ? oldScene.widthProperty() : newScene.widthProperty());
            this.vboxMainRootNode.prefHeightProperty().bind(newScene == null ? oldScene.heightProperty(): newScene.heightProperty());

            this.anchorPaneMainRootNode.prefWidthProperty().bind(newScene == null ? oldScene.widthProperty() : newScene.widthProperty());
            this.anchorPaneMainRootNode.prefHeightProperty().bind(newScene == null ? oldScene.heightProperty(): newScene.heightProperty());

            this.subSceneSimulation.widthProperty().bind(newScene == null ? oldScene.widthProperty() : newScene.widthProperty());
            this.subSceneSimulation.heightProperty().bind(newScene == null ? oldScene.heightProperty(): newScene.heightProperty());

            this.hboxRootToolBar.prefWidthProperty().bind(newScene == null ? oldScene.widthProperty() : newScene.widthProperty());
           // this.hboxRootToolBar.prefHeightProperty().bind(newScene == null ? oldScene.heightProperty(): newScene.heightProperty());

           // this.hboxRootToolBar.setStyle("-fx-border-color: red");
            this.anchorPaneToolBarKit.prefWidthProperty().bind((newScene == null ? oldScene.widthProperty() : newScene.widthProperty()));
           // this.anchorPaneToolBarKit.setStyle("-fx-border-color: blue");

            this.tiltPanePlanets.prefWidthProperty().bind(hboxRootToolBar.prefWidthProperty());
            this.tilePanePlanets.prefWidthProperty().bind((hboxRootToolBar.prefWidthProperty()));
            //this.hboxRootToolBar.layoutYProperty().bind(newScene == null ? oldScene.heightProperty().subtract(hboxRootToolBar.getHeight()): newScene.heightProperty().subtract(hboxRootToolBar.getHeight()));






        });

        vboxMainRootNode.widthProperty().addListener((obs,oldWidth,newWidth)->{
            vboxAddPlanetButton.setLayoutX(newWidth.doubleValue() - 150);


        });
        vboxMainRootNode.heightProperty().addListener((obs,oldHeight,newHeight)->{
            vboxAddPlanetButton.setLayoutY(newHeight.doubleValue() - 150);
            hboxRootToolBar.setLayoutY(newHeight.doubleValue() * 0.88);
        });
    }

    public  void setSubSceneSimulation(){
        this.subSceneSimulation.setFill(Color.BLACK);
        this.subSceneSimulation.setCamera(this.camera);
        this.subSceneSimulation.getRoot().setStyle("-fx-background-color: transparent");
    }
    @FXML
    public  void initialize(){

        buttonAddPlanet.setOnAction(e->{handlerButtonAddPlanetEvent();});
        initializeBinding();

        setSubSceneSimulation();
        handlerTitlePaneEvent();
    }

}
