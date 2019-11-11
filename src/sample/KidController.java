package sample;

import com.playground.datastorage.KidPlayedSessionMemoryStorageImpl;
import com.playground.datastorage.KidStorageMemoryImpl;
import com.playground.datastorage.ObjectLists;
import com.playground.datastorage.PlaygroundStorageMemoryImpl;
import com.playground.kids.Kid;
import com.playground.kids.KidComponent;
import com.playground.kids.KidComponentImpl;
import com.playground.playgrounds.Playground;
import com.playground.playgrounds.PlaygroundComponent;
import com.playground.playgrounds.PlaygroundComponentImpl;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class KidController {
    @FXML
    private TextField kidNameField;
    @FXML
    private TextField kidAgeField;
    @FXML
    private TextField ticketNumberField;
    @FXML
    private RadioButton isVipField;

    private KidComponent kidComponent;
    KidStorageMemoryImpl kidStorage = new KidStorageMemoryImpl();
       PlaygroundStorageMemoryImpl playgroundStorage = new PlaygroundStorageMemoryImpl();
    KidPlayedSessionMemoryStorageImpl kidPlayedSessionStorage = new KidPlayedSessionMemoryStorageImpl();


    public void initialize() {
        kidComponent = new KidComponentImpl(kidStorage,playgroundStorage,kidPlayedSessionStorage);
//        playgroundComponent = new PlaygroundComponentImpl(kidPlayedSessionStorage, playgroundStorage);
    }

    public void getNewKid() {
        boolean isVip = isVipField.isSelected();
        kidComponent.createNewKid(kidNameField.getText(),
                Integer.parseInt(kidAgeField.getText()),
                Integer.parseInt(ticketNumberField.getText()),
                isVip);

    }

}
