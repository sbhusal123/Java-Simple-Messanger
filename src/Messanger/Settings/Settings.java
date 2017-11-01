package Messanger.Settings;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Settings {
    
    protected static Stage guiholder;

    public void loadView() throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Settings");
        primaryStage.getIcons().add(new Image("Resources/setting.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
        
        guiholder = primaryStage;

    }
}
