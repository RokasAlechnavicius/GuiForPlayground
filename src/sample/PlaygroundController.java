package sample;

import com.playground.datastorage.KidPlayedSessionMemoryStorageImpl;
import com.playground.datastorage.KidStorageMemoryImpl;
import com.playground.datastorage.ObjectLists;
import com.playground.datastorage.PlaygroundStorageMemoryImpl;
import com.playground.kids.KidComponentImpl;
import com.playground.kids.KidPlayedSession;
import com.playground.playgrounds.Playground;
import com.playground.playgrounds.PlaygroundComponentImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.List;


public class PlaygroundController {
    @FXML
    private TextField playgroundNameField;
    @FXML
    private TextField amountOfKidsAllowedField;
    @FXML
    private TextField typeOfPlaygroundField;

    private PlaygroundComponentImpl playgroundComponent;
    private KidComponentImpl kidComponent;
    KidStorageMemoryImpl kidStorage = new KidStorageMemoryImpl();
    PlaygroundStorageMemoryImpl playgroundStorage = new PlaygroundStorageMemoryImpl();
    KidPlayedSessionMemoryStorageImpl kidPlayedSessionStorage = new KidPlayedSessionMemoryStorageImpl();

    public void initialize() {
        playgroundComponent = new PlaygroundComponentImpl(kidPlayedSessionStorage, playgroundStorage);
        kidComponent = new KidComponentImpl(kidStorage,playgroundStorage,kidPlayedSessionStorage);
    }

    public String getNewPlayground() {
        if(isInt(amountOfKidsAllowedField)) {
            playgroundComponent.createPlayground(playgroundNameField.getText(), Integer.parseInt(amountOfKidsAllowedField.getText()), typeOfPlaygroundField.getText());
        return "created";
        }
        return "failed";
    }

    public void getNewDoubleSwings() {
        playgroundComponent.createDoubleSwingsPlayground(playgroundNameField.getText(), typeOfPlaygroundField.getText());

    }

    private boolean isInt(TextField input) {
        try{
            int numOfKids = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void getVisitedTodayAmount() {
        Integer count = kidComponent.kidSessionsTodayInAllPlaygroundsCount();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "today's session count: " + count, ButtonType.CANCEL);
        alert.showAndWait();
    }

    public void getVisitedToday() {
        List<KidPlayedSession> sessions = kidComponent.kidSessionsTodayInAllPlaygrounds();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "today's sessions: "
                + sessions.toString()
                , ButtonType.CANCEL);
        alert.showAndWait();
    }
}
