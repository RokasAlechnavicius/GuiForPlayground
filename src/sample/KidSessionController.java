package sample;

import com.playground.datastorage.KidPlayedSessionMemoryStorageImpl;
import com.playground.datastorage.KidStorageMemoryImpl;
import com.playground.datastorage.ObjectLists;
import com.playground.datastorage.PlaygroundStorageMemoryImpl;
import com.playground.kids.Kid;
import com.playground.kids.KidComponentImpl;
import com.playground.kids.KidPlayedSession;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import java.util.List;

public class KidSessionController {
    @FXML
    private ComboBox<Kid> kidComboBox;
    private KidComponentImpl kidComponent;
    KidStorageMemoryImpl kidStorage = new KidStorageMemoryImpl();
    PlaygroundStorageMemoryImpl playgroundStorage = new PlaygroundStorageMemoryImpl();
    KidPlayedSessionMemoryStorageImpl kidPlayedSessionStorage = new KidPlayedSessionMemoryStorageImpl();

    public void initialize() {
        kidComponent = new KidComponentImpl(kidStorage,playgroundStorage,kidPlayedSessionStorage);
        kidComboBox.setItems(FXCollections.observableArrayList(ObjectLists.allKids));
    }

    public void getOneKidSessions() {
            List<KidPlayedSession> sessions = kidComponent.getSessionsForSpecificKid(kidComboBox.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "today's sessions: "
                    + sessions.toString()
                    , ButtonType.CANCEL);
            alert.showAndWait();
    }


}
