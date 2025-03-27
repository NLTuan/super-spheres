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
          this.texturizeBody(bodyBuildTexture.get("Earth"), Color.BLUE);
        } else if (textureName.equals("Mars")) {
            this.texturizeBody(bodyBuildTexture.get("Mars"), Color.ORANGE);
        } else if (textureName.equals("Jupiter")) {
            this.texturizeBody(bodyBuildTexture.get("Jupiter"), Color.ORANGE);
        } else if (textureName.equals("Sun")) {
            this.texturizeBody(bodyBuildTexture.get("Sun"), Color.YELLOW);
        } else if (textureName.equals("Venus")) {
            this.texturizeBody(bodyBuildTexture.get("Venus"),Color.ORANGE);
        } else if (textureName.equals("Mercury")) {
            this.texturizeBody(bodyBuildTexture.get("Mercury"), Color.MEDIUMPURPLE);
        }

    }
    public  void texturizeBody(Image image , Color color){
        PhongMaterial phongMaterial = new PhongMaterial(color);
        phongMaterial.setBumpMap(image);
        phongMaterial.setDiffuseMap(image);
        phongMaterial.setSpecularMap(image);
        phongMaterial.setSelfIlluminationMap(image);
        this.body.setMaterial(phongMaterial);
    }

    public void setTextures(){
        // Earth texture
      // Image image = new Image(getClass().getResource("/BuildInBodiesImages/EarthImage.jpeg").toExternalForm());
        Image imageEarth = new Image("file:/Users/josue/ProjectSpace/ProjectSpace/src/main/resources/fxml/BuildInBodiesImages/EarthImage.jpeg");
        Image imageMars = new Image("file:/Users/josue/ProjectSpace/ProjectSpace/src/main/resources/fxml/BuildInBodiesImages/MarsImage.png");
        Image imageJupiter = new Image("file:/Users/josue/ProjectSpace/ProjectSpace/src/main/resources/fxml/BuildInBodiesImages/JupiterImage.jpg");
        Image imageUranus = new Image("file:/Users/josue/ProjectSpace/ProjectSpace/src/main/resources/fxml/BuildInBodiesImages/UranusImage.png");
        Image imageVenus = new Image("file:/Users/josue/ProjectSpace/ProjectSpace/src/main/resources/fxml/BuildInBodiesImages/VenusImage.png");
        Image imageSun = new Image("file:/Users/josue/ProjectSpace/ProjectSpace/src/main/resources/fxml/BuildInBodiesImages/SolarImage.jpg");
        Image imageMercury= new Image("file:/Users/josue/ProjectSpace/ProjectSpace/src/main/resources/fxml/BuildInBodiesImages/MercuryImage.png");

        bodyBuildTexture.put("Earth",imageEarth);
        bodyBuildTexture.put("Mars",imageMars);
        bodyBuildTexture.put("Jupiter",imageJupiter);
        bodyBuildTexture.put("Uranus",imageUranus);
        bodyBuildTexture.put("Venus",imageVenus);
        bodyBuildTexture.put("Sun",imageSun);
        bodyBuildTexture.put("Mercury",imageMercury);




        //

    }
}
