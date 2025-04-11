package edu.vanier.template.sim;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.SubScene;
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
    
    public Trail(){
    }
    
    
    public void update(Body followBody) {
        if (internalClock % frequency == 0){
            if (trailParticles.size() == maxParticles){
                Group parentNode = (Group) followBody.getParent();
                parentNode.getChildren().remove(trailParticles.get(0));
                trailParticles.remove(0);
            }
            Sphere particle = new Sphere(20);
            trailParticles.add(particle);
            particle.setTranslateX(followBody.getTranslateX());
            particle.setTranslateY(followBody.getTranslateY());
            particle.setTranslateZ(followBody.getTranslateZ());
            
            Group parentNode = (Group) followBody.getParent();
            parentNode.getChildren().add(particle);

        }
        internalClock += 1;
    }
}
