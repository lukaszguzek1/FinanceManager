package controllers;

import helpers.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ExpenseModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListExpensesController implements Initializable {
    public TreeView<String> getProductTreeView() {
        return productTreeView;
    }

    public void setProductTreeView(TreeView<String> productTreeView) {
        this.productTreeView = productTreeView;
    }

    @FXML
    private TextField quantity;
    @FXML
    private TreeView<String> productTreeView;
    @FXML
    private Button addProductButton;
    @FXML
    private Button deleteButton;
    @FXML
    private ComboBox<String> selectBoxProduct;
    @FXML
    private javafx.scene.control.TextField productTextLabel;
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
    private TextField productCategory,productPrice,productName,productType;
    @FXML
    private Label error;


    TreeItem<String> treeItem = new TreeItem<>();
    private Service<Void> productRefresher,productAddToBasket;


    ObservableList<ExpenseModel> observableList = FXCollections.observableArrayList();
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
        Refresher();

    }





    public void Refresher() {
        productRefresher.restart();
    }
}
