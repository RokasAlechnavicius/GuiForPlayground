package sample;

import com.playground.datastorage.KidPlayedSessionMemoryStorageImpl;
import com.playground.datastorage.ObjectLists;
import com.playground.datastorage.PlaygroundStorageMemoryImpl;
import com.playground.kids.Kid;
import com.playground.playgrounds.Playground;
import com.playground.playgrounds.PlaygroundComponent;
import com.playground.playgrounds.PlaygroundComponentImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

public class KidQueueController {
    @FXML
    private ComboBox<Playground> playgroundComboBox;
    @FXML
    private ComboBox<Kid> kidComboBox;
    private PlaygroundComponent playgroundComponent;
    PlaygroundStorageMemoryImpl playgroundStorage = new PlaygroundStorageMemoryImpl();
    KidPlayedSessionMemoryStorageImpl kidPlayedSessionStorage = new KidPlayedSessionMemoryStorageImpl();


    public void initialize() {
        playgroundComponent = new PlaygroundComponentImpl(kidPlayedSessionStorage, playgroundStorage);
        playgroundComboBox.setItems(FXCollections.observableArrayList(ObjectLists.playgrounds));
        kidComboBox.setItems(FXCollections.observableArrayList(ObjectLists.allKids));
    }


    public void AddToQueue() {
        String result = playgroundComponent.enrollInQueue(playgroundComboBox.getValue(), kidComboBox.getValue());
        if (result.equals("playground is full, do you want to be enrolled in queue?")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "enroll in queue? playground is full ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                String result2 = playgroundComponent.acceptsQueue(playgroundComboBox.getValue(), kidComboBox.getValue());
                if(result != null) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION, result2, ButtonType.YES);
                    alert2.showAndWait();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, result, ButtonType.YES);
            alert.showAndWait();
        }
    }

    public void shouldRemoveKidFromQueue() {
        String result = playgroundComponent.removeFromQueue(playgroundComboBox.getValue(), kidComboBox.getValue());
        if(result != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, result, ButtonType.YES);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "removal has failed, maybe kid is not in this queue?", ButtonType.YES);
            alert.showAndWait();
        }

    }

    public void shouldRemoveKidFromPlayground() {
        String result = playgroundComponent.removeFromPlayground(playgroundComboBox.getValue(), kidComboBox.getValue());
        if(result != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, result, ButtonType.YES);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "removal has failed, maybe kid is not in this queue?", ButtonType.YES);
            alert.showAndWait();
        }
    }
}
