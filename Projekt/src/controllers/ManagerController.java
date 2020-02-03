package controllers;

import helpers.DialogsTools;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {

    @FXML
    private BorderPane shopBorderPane;
    @FXML
    private MenuButtonsController menuButtonsController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuButtonsController.setShopController(this);
    }
    public void setCenter(String fxmlPath){
        FXMLLoader loader =  new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent=null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        shopBorderPane.setCenter(parent);
    }
    @FXML
    public void closeApplication( ) {
        Optional<ButtonType> results = DialogsTools.dialogAboutCloseApplication();
        if(results.get()==ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }

    }
    @FXML
    public void setModena( ) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }
    @FXML
    public void setCaspian( ) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }
    public void about(){
        DialogsTools.dialogAboutApplication();
    }
    public void wyswietlListe(){

    }
}

