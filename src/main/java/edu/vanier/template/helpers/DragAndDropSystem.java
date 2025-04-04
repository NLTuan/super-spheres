package edu.vanier.template.helpers;

import edu.vanier.template.sim.Body;
import edu.vanier.template.sim.CameraControlsHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Transform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
    private  boolean isDraggable = true;
    private  CameraControlsHandler cameraControlsHandler;
    public  DragAndDropSystem(TilePane tilePane, Group targetGroup,SubScene subScene, CameraControlsHandler cameraControlsHandler, HBox toolBar){
        this.tilePane = tilePane;
        this.targetGroup = targetGroup;
        this.hBoxToolBar = toolBar;
        this.cameraControlsHandler = cameraControlsHandler;
        this.subScene = subScene;


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
            this.putObjectInFrontOfCamera(draggedObject,0);

            draggedObject = null;
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
    public void DropHandler(){

    }

    public void putObjectInFrontOfCamera(Shape3D shape3D, double distanceFromCamera) {
        Camera camera = cameraControlsHandler.getCamera();

        // 1. Get camera's world transform
        Transform cameraTransform = camera.getLocalToSceneTransform();

        // 2. Define "in front" in camera's LOCAL space (negative Z)
        Point3D localForward = new Point3D(0, 0, -1);

        // 3. Convert to world direction
        Point3D worldForward = cameraTransform.deltaTransform(localForward).normalize();

        // 4. Get camera's world position
        Point3D cameraWorldPos = new Point3D(
                camera.getTranslateX(),
                camera.getTranslateY(),
                camera.getTranslateZ()
        );

        // 5. Calculate spawn position
        Point3D spawnPos = cameraWorldPos.add(worldForward.multiply(distanceFromCamera));

        // 6. Set position (in target group's coordinates)
        Point3D localPos = targetGroup.sceneToLocal(spawnPos);
        shape3D.setTranslateX(localPos.getX());
        shape3D.setTranslateY(localPos.getY());
        shape3D.setTranslateZ(localPos.getZ());

        // DEBUG VISUALS - Uncomment these!
        // addDebugArrow(cameraWorldPos, worldForward); // See method below
        // shape3D.setMaterial(new PhongMaterial(Color.RED)); // Make highly visible
        // ((Sphere)shape3D).setRadius(50); // Make it huge
    }

    public void DragAndDropHandler(){
        transferToSimulation();
    }
}
