package edu.vanier.template.sim;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.util.HashSet;
import java.util.Set;

public class CameraControlsHandler {
    private final Camera camera  = new PerspectiveCamera(true);


    //This is for rotations yaw is about the Z axis (left or right) and pitch is a rotation about the X axis (up and down)
    private double yaw = 0;
    private double pitch = 0;

    private final double MAX_SPEED = 0.3;

    private double speed = 0;


    private  double acceleration = 0.02;

    private  boolean  rightClickedHeld = false;

    private  double lastMouseX, lastMouseY;


    private  final Set<KeyCode> activeKeys = new HashSet<>();

    /**
     * This method will help handle the rotations along the axis ( X,Y)
     * @param event
     */
    private void handleMouseLook(MouseEvent event){
        if(!rightClickedHeld) return;

        //really important for drag detection and self explanatory
        double deltaX = event.getX() - lastMouseX;
        double deltaY = event.getY() - lastMouseY;

        // So here what is being done is based on the drag detections we will set a value for the rotations

        yaw -= deltaX * 0.2;
        pitch -= deltaY * 0.2;

        pitch = Math.max(-89, Math.min(89, pitch) );

        lastMouseX = event.getSceneX();
        lastMouseY = event.getSceneY();
    }

    /**
     * To update the camera's rotation this method use build in properties(transforms)
     */

    public  void updateCameraRotation(){
        // create concateneation is just adding multiple rotations transform in one transform
        //because if we add i in one goe for some reason it did not work
        Transform rotation = new Rotate(yaw,Rotate.Y_AXIS)
                .createConcatenation(new Rotate(pitch, Rotate.X_AXIS));

        camera.getTransforms().setAll(rotation);
    }
}
