package helpers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Changer {

    private static Stage stage;
    public static void changeScene(ActionEvent mouseEvent, String resource){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Changer.class.getResource(resource));
        Parent newParent = null;
        try {
            newParent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newScene = new Scene(newParent);
        Stage newWindow = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage=newWindow;
        stage.setResizable(false);
        newWindow.setScene(newScene);
        newWindow.show();
    }
    public static void AddAccount(){
        //T controller = loader.getController();
        //controller.initData(konto);
    }
    public static Stage getStage(){
        return stage;
    }
}
