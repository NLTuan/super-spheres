package edu.vanier.template.sim;

import edu.vanier.template.math.Vector3D;
import javafx.animation.AnimationTimer;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * @author Josue
 */
public class CameraControlsHandler {

    private PerspectiveCamera camera;

    //This is for rotations yaw is about the Y axis (left or right) and pitch is a rotation about the X axis (up and down)
    private double yaw = 0;
    private double pitch = 0;

    private double MAX_SPEED = 10;

    private double speed = 0;

    private double acceleration = 0.2;

    private boolean rightClickedHeld = false;

    private double lastMouseX, lastMouseY;

    private final Set<KeyCode> activeKeys = new HashSet<>();

    private boolean isMovementAllow = false;

    public CameraControlsHandler(PerspectiveCamera camera) {
        this.camera = camera;
    }

    /**
     * This method will help handle the rotations along the axis ( X,Y)
     *
     * @param event
     */
    private void handleMouseLook(MouseEvent event) {
        if (!rightClickedHeld) {
            return;
        }

        //really important for drag detection and self explanatory
        double deltaX = event.getX() - lastMouseX;
        double deltaY = event.getY() - lastMouseY;

        // So here what is being done is based on the drag detections we will set a value for the rotations
        yaw += (deltaX / 2) * 0.3;
        pitch -= (deltaY / 2) * 0.3;

        pitch = Math.max(-49, Math.min(49, pitch));
        yaw = Math.max(-89, Math.min(89, yaw));

        updateCameraRotation();

        lastMouseX = event.getSceneX();
        lastMouseY = event.getSceneY();
    }

    /**
     * To update the camera's rotation this method use build in
     * properties(transforms)
     */
    public void updateCameraRotation() {
        // create concateneation is just adding multiple rotations transform in one transform
        //because if we add in one goe for some reason it did not work
        Transform rotation = new Rotate(yaw, Rotate.Y_AXIS)
                .createConcatenation(new Rotate(pitch, Rotate.X_AXIS));

        camera.getTransforms().setAll(rotation);
    }

    public void updateMovement() {
        double radYaw = Math.toRadians(yaw);
        double radPitch = Math.toRadians(pitch);

        // this step is crucial since forward is not always forward so we have to get a relative direction
        // just like in roblox where you cant get a look vector, to do that we use our yaw and pitch values
        /*
        Keep in mind javafx's axis are inverted !
         Understand did the trig:
         Math.sin(radYaw) : what it does is essentially take the x and z forward direction
         Math.pitch(radPitch):
         */
        Vector3D vector3DForward = new Vector3D(Math.sin(radYaw) * Math.cos(radPitch), -Math.sin(radPitch), Math.cos(radYaw) * Math.cos(radPitch));

        Vector3D vector3DRight = new Vector3D(Math.cos(radYaw), 0, -Math.sin(radYaw));

        Vector3D vector3DMoveVector = new Vector3D(0, 0, 0);

        if (activeKeys.contains(KeyCode.W)) {
            vector3DMoveVector.addToCurrentVector3D(vector3DForward);
        }
        if (activeKeys.contains(KeyCode.S)) {
            vector3DMoveVector.addToCurrentVector3D(vector3DForward.scaleVector3D(-1));
        }
        if (activeKeys.contains(KeyCode.A)) {
            vector3DMoveVector.addToCurrentVector3D(vector3DRight.scaleVector3D(-1));

        }
        if (activeKeys.contains(KeyCode.D)) {
            vector3DMoveVector.addToCurrentVector3D(vector3DRight);
        }

        if (activeKeys.contains(KeyCode.Q)) {
            vector3DMoveVector.addToCurrentVector3D(new Vector3D(0, 1, 0));
        }

        if (activeKeys.contains(KeyCode.E)) {
            vector3DMoveVector.addToCurrentVector3D(new Vector3D(0, -1, 0));
        }

        if (activeKeys.contains(KeyCode.SPACE)) {
            vector3DMoveVector.addToCurrentVector3D(new Vector3D(0, -1, 0));

        }

        if (activeKeys.contains(KeyCode.SHIFT)) {
            vector3DMoveVector.addToCurrentVector3D(new Vector3D(0, 1, 0));
        }

        // one of the thing that they do well in roblox is they normalize the vector for more accurate movement so I will do the same thing
        vector3DMoveVector.normalizeVector3D();

        //Acceleration effect soon...
        if (!activeKeys.isEmpty()) {
            if (speed > MAX_SPEED) {
                speed = MAX_SPEED;
            }
            speed += acceleration;
        } else {
            // deceleration maybe
            speed *= 0.90; // decreasing by 10 percent every frame
        }

        Vector3D cameraMovements = vector3DMoveVector.scaleVector3D(speed);

        camera.setTranslateX(this.camera.getTranslateX() + cameraMovements.getX());
        camera.setTranslateY(this.camera.getTranslateY() + cameraMovements.getY());
        camera.setTranslateZ(this.camera.getTranslateZ() + cameraMovements.getZ());
    }

    public void handleCamera(Scene scene) {
        if (isMovementAllow) {
            scene.setOnMousePressed(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    rightClickedHeld = true;
                    lastMouseX = event.getSceneX();
                    lastMouseY = event.getSceneY();
                }
            });
            scene.setOnMouseReleased(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    rightClickedHeld = false;
                }
            });

            scene.setOnMouseDragged(this::handleMouseLook);
            scene.setOnKeyPressed(event -> activeKeys.add(event.getCode()));
            scene.setOnKeyReleased(event -> activeKeys.remove(event.getCode()));
        }
    }

    public void setMovementAllow(boolean movementAllow) {
        isMovementAllow = movementAllow;
    }
}
