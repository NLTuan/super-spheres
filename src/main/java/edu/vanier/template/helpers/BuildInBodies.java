package edu.vanier.template.helpers;

import edu.vanier.template.sim.Body;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.paint.PhongMaterial;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BuildInBodies {
    private Body body;

    HashMap<String , Image> bodyBuildTexture = new HashMap<>();

    
    public  BuildInBodies(Body body){
        setTextures();
        this.body = body;
    }
    
    
    public void applyTextures(String textureName){
        if(textureName.equals("Earth")){
            Image image = bodyBuildTexture.get("Earth");
            PhongMaterial phongMaterial = new PhongMaterial(Color.BLUE);
            phongMaterial.setBumpMap(image);
            phongMaterial.setSelfIlluminationMap(image);
            this.body.setMaterial(phongMaterial);
        } else if (textureName.equals("Mars")) {
            
        }
    }

    public void setTextures(){
        // Earth texture
      // Image image = new Image(getClass().getResource("/BuildInBodiesImages/EarthImage.jpeg").toExternalForm());
        Image image = new Image("file:/Users/josue/ProjectSpace/ProjectSpace/src/main/resources/fxml/BuildInBodiesImages/EarthImage.jpeg");
        bodyBuildTexture.put("Earth",image);

        //

    }
}
