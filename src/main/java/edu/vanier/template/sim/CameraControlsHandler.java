package edu.vanier.template.sim;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Set;

public class CameraControlsHandler {
    private final Camera camera  = new PerspectiveCamera(true);


    //This is for rotations yaw is about the Y axis (left or right) and pitch is a rotation about the X axis (up and down)
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

    }
}
