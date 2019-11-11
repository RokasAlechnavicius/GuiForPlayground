package sample;

import com.playground.datastorage.KidPlayedSessionMemoryStorageImpl;
import com.playground.datastorage.KidStorageMemoryImpl;
import com.playground.datastorage.ObjectLists;
import com.playground.datastorage.PlaygroundStorageMemoryImpl;
import com.playground.playgrounds.Playground;
import com.playground.playgrounds.PlaygroundComponentImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Controller extends Application {

    @FXML
    private BorderPane mainPanel;

    @FXML
    private TableView<Playground> playgroundsTable;

    private PlaygroundComponentImpl playgroundComponent;
    KidStorageMemoryImpl kidStorage = new KidStorageMemoryImpl();
    PlaygroundStorageMemoryImpl playgroundStorage = new PlaygroundStorageMemoryImpl();
    KidPlayedSessionMemoryStorageImpl kidPlayedSessionStorage = new KidPlayedSessionMemoryStorageImpl();

    public void initialize() {
        playgroundComponent = new PlaygroundComponentImpl(kidPlayedSessionStorage, playgroundStorage);
        playgroundsTable.setItems(FXCollections.observableArrayList(ObjectLists.playgrounds));
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
    playgroundsTable.setRowFactory(tv -> {
        TableRow<Playground> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if ((!row.isEmpty())) {
                Playground rowData = row.getItem();
                System.out.println(rowData);
            }
        });
        return row;
    });
    }

    @FXML
    public void showAddPlayground () {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("add new playground");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("playgroundDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("couldn't load ");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            PlaygroundController playgroundController = fxmlLoader.getController();
            playgroundController.getNewPlayground();
            playgroundsTable.setItems(FXCollections.observableArrayList(ObjectLists.playgrounds));
        }
    }

    @FXML
    public void showAddDoubleSwings () {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("add new Double swings playground");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("doubleSwingsDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("couldn't load ");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            PlaygroundController playgroundController = fxmlLoader.getController();
            playgroundController.getNewDoubleSwings();
            playgroundsTable.setItems(FXCollections.observableArrayList(ObjectLists.playgrounds));
        }
    }

    @FXML
    public void shouldAddKid() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add new kid");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("kid.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("couldn't load ");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            KidController kidController = fxmlLoader.getController();
            kidController.getNewKid();
        }
    }

    @FXML
    public void shouldAddKidToQueue() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("add kid to queue");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("kidToQueue.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("couldn't load ");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            KidQueueController kidController = fxmlLoader.getController();
            kidController.AddToQueue();
            playgroundsTable.setItems(FXCollections.observableArrayList(ObjectLists.playgrounds));
            playgroundsTable.refresh();
        }
    }

    @FXML
    public void shouldRemoveKidFromQueue() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("remove kid from queue");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("kidToQueue.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("couldn't load ");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            KidQueueController kidController = fxmlLoader.getController();
            kidController.shouldRemoveKidFromQueue();
            playgroundsTable.setItems(FXCollections.observableArrayList(ObjectLists.playgrounds));
            playgroundsTable.refresh();
        }
    }

    @FXML
    public void shouldRemoveKidFromPlayground() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("remove kid from PG");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("kidToQueue.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("couldn't load ");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            KidQueueController kidController = fxmlLoader.getController();
            kidController.shouldRemoveKidFromPlayground();
            playgroundsTable.setItems(FXCollections.observableArrayList(ObjectLists.playgrounds));
            playgroundsTable.refresh();
        }
    }

    @FXML
    public void shouldGetAllVisitedCount() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("playgroundDialog.fxml"));
        fxmlLoader.load();
        PlaygroundController playgroundController = fxmlLoader.getController();
        playgroundController.getVisitedTodayAmount();
    }

    @FXML
    public void shouldGetAllVisited() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("playgroundDialog.fxml"));
        fxmlLoader.load();
        PlaygroundController playgroundController = fxmlLoader.getController();
        playgroundController.getVisitedToday();
    }

    @FXML
    public void shouldGetUtilization() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("utilization.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("couldn't load ");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            PlaygroundUtilizationController playgroundUtilizationController = fxmlLoader.getController();
            playgroundUtilizationController.getUtilization();
        }
    }


    @FXML
    public void shouldGetAllVisitedOneKid() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("kidSingle.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("couldn't load ");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            KidSessionController playgroundUtilizationController = fxmlLoader.getController();
            playgroundUtilizationController.getOneKidSessions();
        }
    }

}
