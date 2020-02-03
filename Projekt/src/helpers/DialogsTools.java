package helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogsTools {
    public static void dialogAboutApplication(){
        Alert informationAlert =  new Alert(Alert.AlertType.INFORMATION);
    informationAlert.setTitle("Informacja");
    informationAlert.setHeaderText("O autorach aplikacji ");
    informationAlert.setContentText("Jesyesmy studentami  trzeciego roku na wydziale cybernetki");
    informationAlert.showAndWait();
    }
    public static Optional<ButtonType> dialogAboutCloseApplication(){
        Alert closeAlert =  new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle("Wyjscie");
        closeAlert.setHeaderText("Czy na pewno chcesz wyjść z aplikacji?");
        Optional<ButtonType> result = closeAlert.showAndWait();
        return result;
    }
    public static Optional<ButtonType> dialogAboutLoggout(){
        Alert closeAlert =  new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle("Wylogowywanie");
        closeAlert.setHeaderText("Czy na pewno chcesz się wylogować?");
        Optional<ButtonType> result = closeAlert.showAndWait();
        return result;
    }
    public static Optional<ButtonType> dialogAboutDeletingAccount(){
        Alert closeAlert =  new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle("Usuwanie konta");
        closeAlert.setHeaderText("Czy na pewno chcesz usunąć to konto?");
        Optional<ButtonType> result = closeAlert.showAndWait();
        return result;
    }
    public static Optional<ButtonType> dialogAboutDeletetingItemFromBasket(){
        Alert closeAlert =  new Alert(Alert.AlertType.CONFIRMATION);
        closeAlert.setTitle("Usuwanie przedmiotu z koszyka");
        closeAlert.setHeaderText("Czy na pewno chcesz usunąć ten przedmiot z koszyka?");
        Optional<ButtonType> result = closeAlert.showAndWait();
        return result;
    }

}
