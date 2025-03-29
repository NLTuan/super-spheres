package edu.vanier.template.helpers;

import edu.vanier.template.sim.Body;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Sphere;

public class DragAndDropSystem {
    private TilePane tilePane;
    private  Group targetGroup;
    private Body draggedObject;
    private double dragOffSetX, dragOffSetY ;
    private HBox hBoxToolBar;
    private double sceneWidth, sceneHeight;
    private double setBackX, setBackY;
    private  boolean isDraggable;
    private PerspectiveCamera perspectiveCamera;
    public  DragAndDropSystem(TilePane tilePane, Group targetGroup, double sceneWidth, double sceneHeight, PerspectiveCamera perspectiveCamera, HBox toolBar){
        this.tilePane = tilePane;
        this.targetGroup = targetGroup;
        this.hBoxToolBar = toolBar;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.perspectiveCamera  = perspectiveCamera;


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
        if(draggedObject != null){
            this.targetGroup.getChildren().add(draggedObject);
            draggedObject.setTranslateZ(draggedObject.getRadius() -this.perspectiveCamera.getTranslateZ() - 100);
        }
    }
    public void updateDragPosition(MouseEvent event){
        double newX = event.getSceneX() - dragOffSetX;
        double newY = event.getSceneY() - dragOffSetY;

        draggedObject.setTranslateX(newX);
        draggedObject.setTranslateY(newY);

        if(draggedObject.getTranslateY() <= -200) {
            transferToSimulation();

        }
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
                if (draggedObject != null){
                    updateDragPosition(e);
                }
            });
        }
    }
    public void DragHandler(){


    }
    public void DropHandler(){

    }

    public void DragAndDropHandler(){

    }
}
