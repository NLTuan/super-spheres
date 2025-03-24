/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.sim;

import edu.vanier.template.math.Vector3D;
import javafx.scene.DepthTest;
import javafx.scene.shape.Sphere;

/**
 *
 * @author letua
 */
public abstract class Body extends Sphere{
    
    private Vector3D position;
    private Vector3D velocity;
    private Vector3D acceleration;
    private Vector3D force;
    
    private double mass;

    public Body(Vector3D position, double mass, double radius) {
        super(radius);
        setDepthTest(DepthTest.ENABLE);
        this.position = position;
        velocity = new Vector3D(0, 0, 0);
        acceleration = new Vector3D(0, 0, 0);
        this.mass = mass;
    }

    public Body(Vector3D position, Vector3D velocity, double mass, double radius, int divisions) {
        super(radius, divisions);
        setDepthTest(DepthTest.ENABLE);
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector3D(0, 0, 0);
        this.mass = mass;
    }

    public Body(Vector3D position, Vector3D velocity, double mass, double radius) {
        super(radius);
        setDepthTest(DepthTest.ENABLE);
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector3D(0, 0, 0);
        this.mass = mass;
    }
    
    public void update(double deltaTime){
        acceleration = force.scaleVector3D(1/mass);
        velocity.addToCurrentVector3D(acceleration.scaleVector3D(deltaTime));
        position.addToCurrentVector3D(velocity.scaleVector3D(deltaTime));
        
        setTranslateX(position.getX());
        setTranslateY(position.getY());
        setTranslateZ(position.getZ());
    }
    public void addForce(Vector3D force){
        this.force = this.force.addVector3D(force);
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

    public Vector3D getForce() {
        return force;
    }

    public void setForce(Vector3D force) {
        this.force = force;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }    
}
