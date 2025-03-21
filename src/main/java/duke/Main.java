package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setMaxWidth(515);
            stage.setMinWidth(515);
            stage.setScene(scene);
            stage.setTitle("L");
            assert stage != null : "stage should not be null";
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
