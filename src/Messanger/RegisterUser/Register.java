package Messanger.RegisterUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Register {

    public  void onCreate() throws Exception {
        
       Stage primaryStage = new Stage();
        
       Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("Register New User");
        primaryStage.getIcons().add(new Image("Resources/loginIcon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }
    
}
