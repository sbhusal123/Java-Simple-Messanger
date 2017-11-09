/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messanger.RegisterUser;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class MainController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField repassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void RegisterClick(ActionEvent event) throws SQLException {

        boolean Regstatus = false;
        
        String Username , Email, Password,Repassword;

        Username = username.getText();
        Email = email.getText();
        Password = password.getText();
        Repassword = repassword.getText();

        if (Username.isEmpty() || Email.isEmpty() || Password.isEmpty() || Repassword.isEmpty()) {
            DisplayError("Empty Fields", "Please provide all the informations.");
        } else {

            if (Password.equals(Repassword)) {
                if (validate(Email)) {

                    Model md = new Model();

                    if (md.checkUserExistence(Username)) {
                        DisplayError("User Registration Failed", "Can't register with existing user-name.");
                    } else {

                        Regstatus = md.addUser(Username, Email, Password);

                        if (Regstatus) {
                            DisplayError("Succesfull", "Registered Succesfullt");

                            username.setText("");

                            password.setText("");

                            repassword.setText("");

                            email.setText("");

                        } else {
                            DisplayError("Error", "Registration Failed");
                        }

                    }
                } else {
                    DisplayError("Invaid Email Format", "Please Enter Valid Email Address.");
                }
            } else {
                DisplayError("Unmatched Passwords", "Please Enter the same passwords for new user.");
            }

        }

    }

    protected  boolean validate(String emailStr) {
        Pattern VALID_EMAIL_ADDRESS_REGEX
                = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    
    protected void DisplayError(String title, String body){
        JOptionPane.showMessageDialog(null,body, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
