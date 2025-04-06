package edu.vanier.template.helpers;

import edu.vanier.template.sim.Body;
import edu.vanier.template.sim.CameraControlsHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;

public class RayCaster {
    private Group groupRoot;
    private CameraControlsHandler cameraControlsHandler;
    private PerspectiveCamera perspectiveCamera;

    public  RayCaster(Group groupRoot, CameraControlsHandler cameraControlsHandler){
        this.groupRoot = groupRoot;
        this.cameraControlsHandler = cameraControlsHandler;
        perspectiveCamera = this.cameraControlsHandler.getCamera();
    }

    public  static  class RayCastResult{
        public Body hitBody;
        public Point3D hitPoint;
        public  double distance;

        public  RayCastResult(Body hitBody, Point3D hitPoint, double distance){
            this.hitBody = hitBody;
            this.hitPoint = hitPoint;
            this.distance = distance;
        }
    }


 public  RayCastResult castRay(double maxDistance){
       return null;
 }

 public  Point3D getCameraWorldPosition(){
        return this.perspectiveCamera.localToScene(Point3D.ZERO);
 }
}
