package edu.vanier.template.helpers;

import edu.vanier.template.math.Vector3D;
import edu.vanier.template.sim.Body;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Text3D extends Group {

    public String textTocheck;
    private Rotate rotateY = new Rotate(0,Rotate.Y_AXIS);
    private Body parentBody;

    private PerspectiveCamera perspectiveCamera;


    private double x,  y,  z,  size;
    Vector3D vector3DPosition = new Vector3D(x,y,z);
    public Text3D(String text, Color color,Body parentBody, PerspectiveCamera camera) {
        this.textTocheck = text; // place holder because im going to use it in build visilazation

        this.parentBody = parentBody;
        this.perspectiveCamera = camera;

        Text meshText = new Text(text);
        meshText.setFill(color);
        meshText.setStyle("-fx-font-size: " + size + "; -fx-font-weight: bold;");

        this.getChildren().add(meshText);
        this.getTransforms().add(rotateY);

}


  public void updatePosition(){
        this.setTranslateX(parentBody.getTranslateX());
        this.setTranslateY(parentBody.getTranslateY() - (parentBody.getRadius()*2));
        this.setTranslateZ(parentBody.getTranslateZ());
        this.rotateToTargetPosition(new Vector3D(perspectiveCamera.getTranslateX(), perspectiveCamera.getTranslateY(),perspectiveCamera.getTranslateZ()));

        Vector3D vector3DDistance = new Vector3D(this.getTranslateX(), this.getTranslateY(), this.getTranslateZ());
        double scaleFactor = Math.min(this.size, 100 / vector3DDistance.getMagnitude());
        this.setSize((vector3DDistance.getMagnitude()/this.size ) * this.size);

        //this.setScaleX(scaleFactor);
        //this.setScaleY(scaleFactor);
        //this.setScaleZ(scaleFactor);
  }

    public void setSize(double size) {
        this.size = size;
    }

    public void rotateToTargetPosition(Vector3D targetPosition){
        double angleOfRotation = Math.atan2(targetPosition.getZ() - this.getTranslateZ(), targetPosition.getX() - this.getTranslateX());
        this.rotateY.setAngle(-Math.toDegrees(angleOfRotation + Math.PI/2));
        this.rotateY.setPivotY(10);
   }

    public void setTextTocheck(String textTocheck) {
        this.textTocheck = textTocheck;
    }

    public String getTextTocheck() {
        return this.textTocheck;
    }
}
