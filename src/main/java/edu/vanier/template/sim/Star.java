package edu.vanier.template.sim;

import edu.vanier.template.math.Vector3D;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;

public class Star extends Body{
    PointLight pointLight = new PointLight(Color.ALICEBLUE);

    public Star(Vector3D position, Vector3D velocity, double mass, double radius, int divisions) {
        super(position, velocity, mass, radius, divisions);
    }

    public Star(Vector3D position, Vector3D velocity, double mass, double d) {
        super(position, velocity, mass, d);
    }

    
    
}
