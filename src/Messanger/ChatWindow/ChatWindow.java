package Messanger.ChatWindow;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Messanger.Login.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class ChatWindow {

    Messanger.ChatWindow.Controller chatWindowControllerInstance;

    protected static Stage cW;

    public void OnCreate(Stage ps) throws IOException {
//        asd
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image("Resources/loginIcon.png"));
        primaryStage.setTitle("ChatWindow - " + Controller.SESSION_usrname);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        ps.close();
        cW = primaryStage;
        
    }

}
