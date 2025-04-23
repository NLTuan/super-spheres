package edu.vanier.template.controllers;

import edu.vanier.template.ui.MainApp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML controller  for the start page.
 *
 * @author Joseph Josue Forestal
 */
public class StartPageController {
    @FXML
    private Label labelTitle;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonExit;
    
    @FXML
    private Button logInButton;
    
    @FXML 
    private Button signUpButton;

    @FXML
    private Button signUpSubmit;
    
    @FXML
    private Button logInSubmit;
    
    @FXML
    private Button backButtonLogIn;
    
    @FXML
    private Button backButtonSignUp;
    
    @FXML
    private VBox logInWindow;
    
    @FXML
    private VBox signUpWindow;
    
    @FXML
    private TextField logInName;
    
    @FXML
    private PasswordField logInPassword;
    
    @FXML
    private TextField signUpName;
    
    @FXML
    private PasswordField signUpPassword;
    
    @FXML
    public void initialize() {
        buttonStart.setOnAction(event -> handleStartButton());
        buttonExit.setOnAction(event -> handleExitButton());
        logInButton.setOnAction(event -> handleLogInButton());
        signUpButton.setOnAction(event -> handleSignUpButton());
        
        backButtonSignUp.setOnAction(event -> handleSignUpBack());
        backButtonLogIn.setOnAction(event -> handleLogInBack());
        
        signUpSubmit.setOnAction(event -> handleSignUpSubmit());
        logInSubmit.setOnAction(event -> handleLogInSubmit());
    }
    
    public void handleSignUpSubmit(){
        String name = signUpName.getText();
        String password = signUpPassword.getText();
        
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/save/users.csv", true));
            writer.println(name + ',' + password);
            
            logInButton.setDisable(false);
            signUpButton.setDisable(false);
            signUpWindow.toBack();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void handleLogInSubmit(){
        String enteredName = logInName.getText();
        String enteredPassword = logInPassword.getText();

        System.out.println(enteredName + enteredPassword);
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/save/users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String password = parts[1];
                System.out.println(name + password);

                if(name.equals(enteredName) && password.equals(enteredPassword)){
                    logInButton.setDisable(false);
                    signUpButton.setDisable(false);
                    logInWindow.toBack();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
    }
    }
    
    public void handleLogInButton(){
        logInButton.setDisable(true);
        signUpButton.setDisable(true);
        logInWindow.toFront();
    }
    public void handleSignUpButton(){
        logInButton.setDisable(true);
        signUpButton.setDisable(true);
        signUpWindow.toFront();
    }
    
    public void handleSignUpBack(){
        logInButton.setDisable(false);
        signUpButton.setDisable(false);
        signUpWindow.toBack();

    }
    
    public void handleLogInBack(){
        logInButton.setDisable(false);
        signUpButton.setDisable(false);
        logInWindow.toBack();
    }
    
    public void handleStartButton(){
        MainApp.switchScene(MainApp.TEMPLATE_SELECTION_LAYOUT);
    }

    public void handleExitButton() {
        Platform.exit();
    }

}

