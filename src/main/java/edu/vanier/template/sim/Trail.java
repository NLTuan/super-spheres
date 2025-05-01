package edu.vanier.template.sim;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

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
    int maxParticles = 50;
    
    Color trailColor;
    
    public Trail(){
        trailColor = Color.WHITE;
    }
    
    
    public void update(Body followBody) {
        if (internalClock % frequency == 0){
            if (trailParticles.size() == maxParticles){
                Group parentNode = (Group) followBody.getParent();
                parentNode.getChildren().remove(trailParticles.get(0));
                trailParticles.remove(0);
            }
            
            Sphere particle = new Sphere(5);
            PhongMaterial material = new PhongMaterial();
            
            material.setDiffuseColor(Color.rgb(
                    (int)(trailColor.getRed() * 255), 
                    (int)(trailColor.getGreen() * 255), 
                    (int)(trailColor.getBlue() * 255), 
                    trailColor.getOpacity())
            );
            particle.setMaterial(material);
            
            trailParticles.add(particle);
            particle.setTranslateX(followBody.getTranslateX());
            particle.setTranslateY(followBody.getTranslateY());
            particle.setTranslateZ(followBody.getTranslateZ());
            
            Group parentNode = (Group) followBody.getParent();
            parentNode.getChildren().add(particle);

        }
        internalClock += 1;
    }

    public Color getTrailColor() {
        return trailColor;
    }

    public void setTrailColor(Color trailColor) {
        this.trailColor = trailColor;
    }
    
    
}
