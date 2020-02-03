package controllers;

import helpers.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.ExpenseModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditExpensesController implements Initializable {

    private Service<Void> productRefresher,editProduct;
    ObservableList<ExpenseModel> observableList = FXCollections.observableArrayList();
    @FXML
    private TableView<ExpenseModel> productTableView;
    @FXML
    private TableColumn<ExpenseModel, String> col_Expense;
    @FXML
    private TableColumn<ExpenseModel, Integer> col_Type;
    @FXML
    private TableColumn<ExpenseModel, Double> col_Cost;
    @FXML
    private TableColumn<ExpenseModel, Integer> col_Category;
    @FXML
    private Label error;
    @FXML
    private TextField productName,productType,productCategory,productPrice;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productRefresher =  new Service<Void>(){

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            Connection connection = new DataBase().getConnection();
                            ResultSet resultSet = connection.createStatement().executeQuery("Select * From Products");
                            observableList.remove(0,observableList.size());
                            while (resultSet.next()) {
                                observableList.add(new ExpenseModel(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4)));
                            }
                            connection.close();
                        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        }

                        col_Expense.setCellValueFactory(new PropertyValueFactory("Name"));
                        col_Cost.setCellValueFactory(new PropertyValueFactory("price"));
                        col_Type.setCellValueFactory(new PropertyValueFactory("Type"));
                        col_Category.setCellValueFactory(new PropertyValueFactory("Category"));
                        productTableView.setItems(observableList);
                        return null;
                    }
                };
            }
        };
        editProduct =  new Service<Void>(){

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected void succeeded() {
                        Refresher();
                    }
                    @Override
                    protected Void call() throws Exception {
                        try {
                            Connection connection = new DataBase().getConnection();
                            connection.createStatement().execute("UPDATE Products SET Name=\'"+productName.getText()+"\', Category=\'"+Integer.valueOf(productType.getText())+"\', Price=\'"+Double.valueOf(productPrice.getText())+"\', Category=\'"+Integer.valueOf(productCategory.getText())+"\' WHERE Name=\'"+productTableView.getSelectionModel().getSelectedItem().getName()+"\'");
                            productName.setText("");
                            productCategory.setText("");
                            productPrice.setText("");
                            productType.setText("");
                            connection.close();
                        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
            }
        };
        Refresher();
    }

    public void regainProduct(MouseEvent mouseEvent) {
        productName.setText(productTableView.getSelectionModel().getSelectedItem().getName());
        productCategory.setText(String.valueOf(productTableView.getSelectionModel().getSelectedItem().getCategory()));
        productPrice.setText(String.valueOf(productTableView.getSelectionModel().getSelectedItem().getPrice()));
        productType.setText(String.valueOf(productTableView.getSelectionModel().getSelectedItem().getType()));
    }

    public void editProduct(ActionEvent actionEvent) {
        if(productType.getText().matches("[0-9]+")&&productCategory.getText().matches("[0-9]+")&&productPrice.getText().matches("[0-9]+.?[0-9]*")){
            error.setVisible(false);
            editProduct.restart();
        }else{
            error.setVisible(true);
        }
    }
    public void Refresher() {
        productRefresher.restart();
    }
}
