package controllers;

import helpers.DataBase;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BoostAccountController implements Initializable {

    @FXML
    private TextField amount;
    @FXML
    private  Label wallet;
    @FXML
    private TextField amount1;
    @FXML
    private  Label wallet1;
    String myMoney;
    String myMoney1;
    private Service<Void> boostAccount;
    public void boost(ActionEvent actionEvent) {

        boostAccount.restart();

    }


    public void Refresher() {
        wallet.setText(myMoney);
        amount.setText("0");
        wallet1.setText(myMoney1);
        amount1.setText("0");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boostAccount =  new Service<Void>(){
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
                            double money=0;
                            double money1=0;
                            ResultSet rs=connection.createStatement().executeQuery("SELECT Wartosc From money WHERE Nazwa='Cash'");
                            ResultSet rs1=connection.createStatement().executeQuery("SELECT Wartosc From money WHERE Nazwa='Transfer'");
                            while (rs.next()&rs1.next()){
                                money=rs.getDouble("Wartosc");
                                money1=rs.getDouble("Wartosc");
                            }
                            if(amount.getText()!=null&&amount.getText().matches("[0-9]+")&&amount1.getText()!=null&&amount1.getText().matches("[0-9]+")) {
                                connection.createStatement().executeUpdate("UPDATE money Set Wartosc=\'" + (money + Double.valueOf(amount.getText())) + "\' WHERE Nazwa='Cash'");
                                connection.createStatement().executeUpdate("UPDATE money Set Wartosc=\'" + (money1 + Double.valueOf(amount1.getText())) + "\' WHERE Nazwa='Transfer'");
                            }
                            rs=connection.createStatement().executeQuery("SELECT Wartosc From money WHERE Nazwa='Cash'");
                            rs1=connection.createStatement().executeQuery("SELECT Wartosc From money WHERE Nazwa='Transfer'");
                            while (rs.next()&&rs1.next()){
                                money=rs.getDouble("Wartosc");
                                money1=rs1.getDouble("Wartosc");
                            }
                            myMoney="Stan gotówki: "+money+" zl";
                            myMoney1="Stan konta: "+money1+" zl";
                            connection.close();
                        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
            }
        };
        boostAccount.start();
    }
}
