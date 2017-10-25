package javasocket;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class JavaSocket extends Application {
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
        
        Parent root = FXMLLoader.load(getClass().getResource("server.fxml"));
        
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
