package Messanger.Login;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Messanger.ChatWindow.ChatWindow;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Messanger.RegisterUser.Register;

public class Controller {
    
    public static String SESSION_usrname;

    @FXML
    TextField username;
    @FXML
    TextField password;
    
    @FXML 
    Label msg;

    Model md = new Model();
    
    @FXML
    
    protected void registerUser() throws Exception{
        
        Register reg  = new Register();
        reg.onCreate();       
    }
    
    @FXML
    protected void btnClick() throws IOException, ClassNotFoundException {
        String usrname = username.getText();
        String pass = password.getText();
        SESSION_usrname = usrname;
        try {
            if(md.checkLogin(usrname, pass)){
                ChatWindow cW = new ChatWindow();
                cW.OnCreate(Login.ps);
            }else{
                msg.setText("Invalid username and password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
