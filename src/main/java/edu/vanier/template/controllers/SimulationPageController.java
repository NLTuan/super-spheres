package edu.vanier.template.controllers;

import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.awt.*;

/**
 * Fxml controller for simulation page
 *
 * @author Joseph Josue Forestal
 */
public class SimulationPageController {
    private String stringTemplateName;
    @FXML
    StackPane stackPaneRootNode;
    @FXML
    SubScene subSceneSimulationPage;
    @FXML
    Button buttonAddPlanet;
    @FXML
    public void initialize(){
        // handles the logic of the add button
        buttonAddPlanet.setOnAction(e->{});
    }

    public  void addPlanet(){
        //add planet logic, this is probably going to a switch scene customization
    }
    public  SimulationPageController(String stringTemplateName){
        this.stringTemplateName = stringTemplateName;
    }



    public void loadAssets(){
        if (this.stringTemplateName.equals( null) ||(this.stringTemplateName.isEmpty() || this.stringTemplateName.isBlank())){return;}
        if(this.stringTemplateName.equals("")){
            //Add logic
        } else if (this.stringTemplateName.equals("")) {
            //add logic
        }
        else {
//add logic
        }
    }
}
