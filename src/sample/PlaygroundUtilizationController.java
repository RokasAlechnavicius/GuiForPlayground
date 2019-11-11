package sample;

import com.playground.datastorage.ObjectLists;
import com.playground.playgrounds.Playground;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

public class PlaygroundUtilizationController {

    @FXML
    private ComboBox<Playground> playgroundComboBox;

    public void initialize() {
        playgroundComboBox.setItems(FXCollections.observableArrayList(ObjectLists.playgrounds));
    }

    public void getUtilization() {
        Integer utilization = playgroundComboBox.getValue().calculateUtilization();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Utilization for this playground is " + utilization + "%", ButtonType.OK);
        alert.showAndWait();
    }
}
