/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.sim;

import edu.vanier.template.controllers.SimulationMainPageController;
import edu.vanier.template.controllers.TemplateSelectionController;
import edu.vanier.template.helpers.DragAndDropSystem;
import edu.vanier.template.helpers.ListViewBodies;
import edu.vanier.template.helpers.Text3D;
import edu.vanier.template.math.Physics;
import edu.vanier.template.math.Vector3D;
import javafx.scene.DepthTest;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author letua
 */
public abstract class Body extends Sphere{
    private final static Logger logger = LoggerFactory.getLogger(Body.class);
    protected Vector3D position;
    protected Vector3D velocity;
    protected Vector3D acceleration;
    protected Vector3D force;
    
    protected double mass;

    private String name;
    public Text3D nameLabel;

    protected ListViewBodies listViewBodies;

    Trail trail;

    public Body(Vector3D position, double mass, double radius) {
        super(radius);
        this.trail = new Trail();
        this.listViewBodies = new ListViewBodies("No name",this);
        setDepthTest(DepthTest.ENABLE);
        this.position = position;
        velocity = new Vector3D(0, 0, 0);
        acceleration = new Vector3D(0, 0, 0);
        this.mass = mass;
        createNameLabel();
    }

    public Body(Vector3D position, Vector3D velocity, double mass, double radius, int divisions) {
        super(radius, divisions);
        this.trail = new Trail();
        this.listViewBodies = new ListViewBodies("No name",this);
        setDepthTest(DepthTest.ENABLE);
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector3D(0, 0, 0);
        this.mass = mass;
        createNameLabel();
    }

    public Body(Vector3D position, Vector3D velocity, double mass, double radius) {
        super(radius);
        this.trail = new Trail();
        this.listViewBodies = new ListViewBodies("No name",this);
        setDepthTest(DepthTest.ENABLE);
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector3D(0, 0, 0);
        this.mass = mass;
        createNameLabel();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(double deltaTime){
        trail.update(this);
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

    @Override
    public String toString() {
        return "Body{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", acceleration=" + acceleration +
                ", force=" + force +
                ", mass=" + mass +
                '}';
    }

    private void createNameLabel(){
        this.nameLabel = new Text3D(this.name != null ? this.name : "Planet", Color.WHITE, this,SimulationMainPageController.getLastInstance().getCamera());
        this.nameLabel.setVisible(false);
        this.nameLabel.setManaged(false);
    }
    public  void setShowName(boolean showName){
        if(nameLabel != null){
            nameLabel.setVisible(showName);
            nameLabel.setManaged(showName);
        }
    }
    public  void updateLabelPosition(){
        if(this.nameLabel != null){
            this.nameLabel.updatePosition();
        }
    }
    public  double vOrbital(Body bodyCentral, double distance){
        if(bodyCentral == null || bodyCentral.getMass() <= 0) {
            logger.info("body central or body.getmass  is less than zero");
            return 0;
        }
        if(bodyCentral.getMass() <= 1e-10){
            logger.info("body central  mass is less tjam 1e-10");
            return 0;}
        double mu = Physics.G * bodyCentral.getMass();
        return Math.sqrt(mu / distance);
    }
}
