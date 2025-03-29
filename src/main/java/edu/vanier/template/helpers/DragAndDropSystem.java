package edu.vanier.template.helpers;

import edu.vanier.template.sim.Body;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Sphere;

public class DragAndDropSystem {
    private TilePane tilePane;
    private  Group targetGroup;
    public  DragAndDropSystem(TilePane tilePane, Group targetGroup){
        this.tilePane = tilePane;
        this.targetGroup = targetGroup;
    }

    public  void setTilePaneEventConsumers(){
        if (this.tilePane == null) return;
        else {
            this.tilePane.setPickOnBounds(false);
            for(int i = 0; i <= tilePane.getChildren().size(); i++){
                if (tilePane.getChildren().get(i) instanceof Sphere) {
                    tilePane.getChildren().get(i).setPickOnBounds(true);
                }
            }
        }
    }


    public void setUpBodyDrag(){

    }
    public void DragHandler(Body instance){

    }
    public void DropHandler(){

    }

    public void DragAndDropHandler(){

    }
}
