package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class Main extends Application {
    private double xOffset=0,yOffset=9;
    private static Stage stage=null;
    public static final String Login_FXML = "../fxml/manager.fxml";

    //TODO jesli ktos sie wyloguje nalezy usunac go z validation!!!!!!
    //TODO w darka progamie dodac inita w ktorym dodamy Konto do sceny aby moc wyswietlic np imie i dostac sie do uprawnien itp bo od tego bedzie zalezało ilosc mozliwosci
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(Login_FXML));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed((MouseEvent event)->{
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event)->{
           primaryStage.setX(event.getScreenX()-xOffset);
           primaryStage.setY(event.getScreenY()-yOffset);
        });
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        stage=primaryStage;
        primaryStage.show();
    }

    public static Stage getStage(){
        return  stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
