package edu.vanier.template.helpers;

import edu.vanier.template.math.Vector3D;
import edu.vanier.template.sim.Body;
import edu.vanier.template.sim.BodyHandler;
import edu.vanier.template.sim.CameraControlsHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.event.ActionEvent;


import java.awt.*;

public class DragAndDropSystem {
    private final static Logger logger = LoggerFactory.getLogger(DragAndDropSystem.class);
    private TilePane tilePane;
    private  Group targetGroup;
    private Body draggedObject;
    private double dragOffSetX, dragOffSetY ;
    private HBox hBoxToolBar;
    private double sceneWidth, sceneHeight;
    private double setBackX, setBackY;
    private SubScene subScene;
    private javafx.scene.control.TextField textFieldVelocity;
    private BodyHandler bodyHandler;
    private  boolean isDraggable = true;
    private  CameraControlsHandler cameraControlsHandler;
    public  DragAndDropSystem(TilePane tilePane, Group targetGroup, SubScene subScene, CameraControlsHandler cameraControlsHandler, HBox toolBar, TextField textField , BodyHandler bodyHandler){
        this.tilePane = tilePane;
        this.targetGroup = targetGroup;
        this.hBoxToolBar = toolBar;
        this.cameraControlsHandler = cameraControlsHandler;
        this.subScene = subScene;
        this.textFieldVelocity  = textField;
        this.bodyHandler = bodyHandler;

        setTilePaneEventConsumers();

    }

    public  void setTilePaneEventConsumers(){
        if (this.tilePane == null) return;
        else {
            this.tilePane.setPickOnBounds(false);
            for(int i = 0; i < tilePane.getChildren().size(); i++){
                if (tilePane.getChildren().get(i) instanceof Sphere) {
                    tilePane.getChildren().get(i).setPickOnBounds(true);
                    setUpBodyDrag(tilePane.getChildren().get(i));
                }
            }
        }
    }

    public  void startDrag(Body body, MouseEvent event){

            draggedObject = body;
            dragOffSetX = event.getSceneX() - body.getTranslateX();
            dragOffSetY = event.getSceneY() - body.getTranslateY();


    }


    public void transferToSimulation(){
        if(draggedObject != null && cameraControlsHandler != null){
            this.targetGroup.getChildren().add(draggedObject);
            this.putObjectInFrontOfCamera(draggedObject,500);
            Body cloneDragged = draggedObject;
            this.DropHandler(cloneDragged);
            //draggedObject = null;
        }
    }
    public void updateDragPosition(MouseEvent event){
        if(!isDraggable) return;
        double newX = event.getSceneX() - dragOffSetX;
        double newY = event.getSceneY() - dragOffSetY;

        draggedObject.setTranslateX(newX);
        draggedObject.setTranslateY(newY);

        transferToSimulation();

    }
    public void setUpBodyDrag(Node node){
        if(node instanceof Body body){
            body.setOnMousePressed(e->{
                if(e.getButton() == MouseButton.PRIMARY){
                    startDrag(body, e);
                    e.consume();

                }
            });

            body.setOnMouseDragged(e->{
                if (draggedObject != null && cameraControlsHandler != null && tilePane.getChildren().contains(draggedObject)){
                    updateDragPosition(e);
                }
            });
        }
    }
    public void DragHandler(){


    }
    public void DropHandler(Body draggedObject){
        if(this.bodyHandler == null) return;
            textFieldVelocity.setVisible(true);
            textFieldVelocity.setManaged(true);
        try{
            logger.info("inside drop handler");


            Point3D lookVector = cameraControlsHandler.getLookVector();


            this.textFieldVelocity.setOnKeyPressed(e->{
                try {
                    double velocityValue = Double.parseDouble(textFieldVelocity.getText());
                    Point3D velocityVector = lookVector.multiply(velocityValue);
                    Vector3D vector3DVelocity = new Vector3D(velocityVector.getX(), velocityVector.getY(), velocityVector.getZ());


                    if (e.getCode() == KeyCode.ENTER) {
                        logger.info("key pressed");

                        draggedObject.setVelocity(vector3DVelocity);
                        bodyHandler.add(draggedObject);
                        this.textFieldVelocity.setVisible(false);
                        this.textFieldVelocity.setManaged(false);
                    }

                }catch (Exception exception){
                    logger.info( exception.getMessage()+"Exception");
                }
            });

        }catch (Exception exception){
            logger.info(exception.getMessage());
        }
    }

    public void putObjectInFrontOfCamera(Shape3D shape3D, double distanceFromCamera) {
        PerspectiveCamera perspectiveCamera = cameraControlsHandler.getCamera();
        logger.info("camera: x={}, y={}, z={}", perspectiveCamera.getTranslateX(), perspectiveCamera.getTranslateY(), perspectiveCamera.getTranslateZ());
        Point3D point3DCameraPosition =  targetGroup.sceneToLocal(perspectiveCamera.localToScene(
                0,0,0
        ));
        Point3D point3DLookVector = cameraControlsHandler.getLookVector();

        Point3D point3DSpawnPosition = point3DCameraPosition.add(point3DLookVector.multiply(distanceFromCamera));
        if(shape3D != null){
            shape3D.setTranslateX(point3DSpawnPosition.getX());
            shape3D.setTranslateY(point3DSpawnPosition.getY());
            shape3D.setTranslateZ(point3DSpawnPosition.getZ());
            logger.info("sphere position x={}, y={}, z={}", shape3D.getTranslateX(), shape3D.getTranslateY(),shape3D.getTranslateZ());
        }
    }
    public void DragAndDropHandler(){
        transferToSimulation();
    }
}
