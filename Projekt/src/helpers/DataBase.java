package helpers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.*;

public class DataBase {
    @FXML
    private ComboBox<String> selectBoxProduct;
    public Connection connect;





    public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        connect= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/KxWZ87xFl9?useTimezone=true&serverTimezone=UTC","KxWZ87xFl9","QNJBJXFDII");
        return connect;
    }




    public void addProduct(String name, String Type, double price, String Category) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        getConnection();
        String SQLquery1="INSERT INTO Products VALUES ('"+name+"','"+Type+"','"+price+"','"+Category+"')";
        Statement statement=connect.createStatement();
        statement.executeUpdate(SQLquery1);
        connect.close();
    }



    public void deleteProduct(String productName) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        getConnection();
        String SQLquery1="DELETE FROM Products WHERE Name=\'"+productName+"\'";
        Statement statement=connect.createStatement();
        statement.execute(SQLquery1);
        connect.close();

    }
}

