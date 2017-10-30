package Messanger.Login;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class Login extends Application {
    
    protected static Stage ps;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
        
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("Resources/loginIcon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        ps = primaryStage;
    }
    
    public void loadView() throws IOException{
        
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("Resources/loginIcon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        ps = primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
