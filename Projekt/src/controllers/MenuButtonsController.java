package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuButtonsController implements Initializable {
    public static final String ListProduct_FXML = "../fxml/listExpense.fxml";

    public static final String addProduct_FXML = "../fxml/addExpense.fxml";
    private ManagerController shopController;

    @FXML
    private ToggleGroup toggleButtons;
    @FXML
    private Button addProduct,editProducts,editAccounts;

    @FXML
    public void openListProduct() {
        shopController.setCenter(ListProduct_FXML);
    }




    @FXML
    public void addProduct( ) {
        if (toggleButtons.getSelectedToggle()!=null){
            toggleButtons.getSelectedToggle().setSelected(false);
        }
        shopController.setCenter(addProduct_FXML);
    }

    public void setShopController(ManagerController shopController) {
        this.shopController = shopController;
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void statistic(ActionEvent actionEvent) {
        shopController.setCenter("../fxml/statistic.fxml");
    }
    public void boostAccount(ActionEvent actionEvent) {
        shopController.setCenter("../fxml/boostAccount.fxml");
    }

    public void editProducts(ActionEvent actionEvent) {
        shopController.setCenter("../fxml/editExpense.fxml");
    }


}
