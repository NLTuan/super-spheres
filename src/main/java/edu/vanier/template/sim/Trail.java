package edu.vanier.template.sim;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javax.swing.plaf.metal.MetalIconFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author letua
 */
public class Trail {
    ArrayList<Sphere> trailParticles = new ArrayList<>();
        
    int frequency = 3;
    int internalClock = 0;
    int maxParticles = 100;
    boolean isActive = true;

    private Color colorTrail = Color.WHITE;
    public PhongMaterial phongMaterialTrail = new PhongMaterial(this.colorTrail);
    
    public Trail(){
    }
    
    
    public void update(Body followBody) {
        if( !isActive || followBody == null) return;
        if (internalClock % frequency == 0){
            if (trailParticles.size() == maxParticles){
                Group parentNode = (Group) followBody.getParent();
                parentNode.getChildren().remove(trailParticles.get(0));
                trailParticles.remove(0);
            }
            Sphere particle = new Sphere(5);
            particle.setMaterial(this.phongMaterialTrail);
            trailParticles.add(particle);
            particle.setTranslateX(followBody.getTranslateX());
            particle.setTranslateY(followBody.getTranslateY());
            particle.setTranslateZ(followBody.getTranslateZ());
            
            Group parentNode = (Group) followBody.getParent();
            parentNode.getChildren().add(particle);

        }
        internalClock += 1;
    }

    public void setTrailColor(Color newColor) {
        this.phongMaterialTrail.setDiffuseColor(newColor);
        this.phongMaterialTrail.setSpecularColor(colorTrail);
    }

    /**
     * This method set the activate state
     * @param activeState, the active state parameter
     */
    public void setActive(boolean activeState){
        this.isActive = activeState;
    }

    public ArrayList<Sphere> getTrailParticles() {
        return trailParticles;
    }
}
