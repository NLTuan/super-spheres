/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.sim;

import edu.vanier.template.math.Vector3D;
import javafx.scene.shape.Sphere;

/**
 *
 * @author letua
 */
public abstract class Body extends Sphere{
    
    private Vector3D position;
    private Vector3D velocity;
    private Vector3D acceleration;
    
    private double mass;
//    private double radius;

    public Body(Vector3D position, Vector3D velocity, Vector3D acceleration, double mass, double radius, int divisions) {
        super(radius, divisions);
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
    }

    public Body(Vector3D position, Vector3D velocity, Vector3D acceleration, double mass, double d) {
        super(d);
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
    }

    
    
    
    public void update(){
        
    }
    
    
    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Vector3D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3D velocity) {
        this.velocity = velocity;
    }

    public Vector3D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector3D acceleration) {
        this.acceleration = acceleration;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }    
}
