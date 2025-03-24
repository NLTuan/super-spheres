package edu.vanier.template.sim;

import edu.vanier.template.math.Vector3D;

public class Planet extends Body{

    public Planet(Vector3D position, Vector3D velocity, Vector3D acceleration, double mass, double radius, int divisions) {
        super(position, velocity, acceleration, mass, radius, divisions);
    }

    public Planet(Vector3D position, Vector3D velocity, Vector3D acceleration, double mass, double d) {
        super(position, velocity, acceleration, mass, d);
    }
}
