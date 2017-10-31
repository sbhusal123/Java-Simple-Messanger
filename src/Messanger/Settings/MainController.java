package Messanger.Settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import Messanger.Login.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;

public class MainController implements Initializable {

    @FXML
    GridPane setting_scence;

    @FXML
    Label displayStatus;

    @FXML
    Label displayUsername;

    @FXML
    Label displayEmail;
    
    Model md = new Model();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Model.infoObject info = md.getUserInfo(Controller.SESSION_usrname);
        displayEmail.setText(info.email);
        displayUsername.setText(Controller.SESSION_usrname);
        if (info.accountStatus.equals("0")) {
            displayStatus.setText("Unverified Email");
        } else {
            displayStatus.setText("Verified");
        }
    }

    @FXML
    protected void settings_button(ActionEvent ev) {
        setting_scence.getChildren().clear();
        String button_Style = " -fx-background-color:\n"
                + "        linear-gradient(#f0ff35, #a9ff00),\n"
                + "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);\n"
                + "    -fx-background-radius: 6, 5;\n"
                + "    -fx-background-insets: 0, 1;\n"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n"
                + "    -fx-text-fill: #395306;";

        String button_hover = "-fx-background-color: \n"
                + "        linear-gradient(#f2f2f2, #d6d6d6),\n"
                + "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n"
                + "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n"
                + "    -fx-background-radius: 8,7,6;\n"
                + "    -fx-background-insets: 0,1,2;\n"
                + "    -fx-text-fill: black;\n"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );";

        String btntext = ((Labeled) ev.getSource()).getText();
        if (btntext.equals("Change Password")) {

            Label txt1 = new Label("Old Password");
            txt1.setStyle("-fx-padding: 10 10 10 100;-fx-font: 20px \"Serif\"; -fx-text-fill: indigo;");

            PasswordField old_pass = new PasswordField();
            old_pass.setStyle(" -fx-max-width: 200;");
            old_pass.setPromptText("Type Old password");

            PasswordField re_password = new PasswordField();
            re_password.setStyle(" -fx-max-width: 200;");
            re_password.setPromptText("Retype new password");

            Label txt2 = new Label("New Password");
            txt2.setStyle("-fx-padding: 10 10 10 100;-fx-font: 20px \"Serif\"; -fx-text-fill: indigo;");

            Label txt3 = new Label("Retype Password");
            txt3.setStyle("-fx-padding: 10 10 10 100;-fx-font: 20px \"Serif\"; -fx-text-fill: indigo;");

            PasswordField new_pass = new PasswordField();
            new_pass.setStyle(" -fx-max-width: 200;");
            new_pass.setPromptText("Type New password");
            Button submit = new Button("Submit");
            submit.setStyle(button_Style);

            submit.setOnMouseEntered(e -> submit.setStyle(button_hover));
            submit.setOnMouseExited(e -> submit.setStyle(button_Style));

            setting_scence.getChildren().clear();
            setting_scence.add(txt1, 0, 0);
            setting_scence.add(old_pass, 1, 0);
            setting_scence.add(txt2, 0, 1);
            setting_scence.add(new_pass, 1, 1);
            setting_scence.add(txt3, 0, 2);
            setting_scence.add(re_password, 1, 2);
            setting_scence.add(submit, 2, 2);
            
             submit.setOnAction((ActionEvent ev1) -> {
                 if(new_pass.getText().isEmpty() || old_pass.getText().isEmpty() || re_password.getText().isEmpty()){
                     ShowError("Error", "Fields Missing");
                 }
                 else if(!new_pass.getText().equals(re_password.getText())){
                     
                     ShowError("Error", "Password do not match");
                 }else{
                     md.updatePassword(old_pass.getText(), new_pass.getText(),Controller.SESSION_usrname);
                 }

            });
             
        } else if (btntext.equals("Change Email")) {

            Label txt1 = new Label("Type Valid Email Address");
            txt1.setStyle("-fx-padding: 10 10 10 20;-fx-font: 20px \"Serif\"; -fx-text-fill: indigo;");

            TextField email = new TextField();
            email.setStyle(" -fx-max-width: 200");
            email.setPromptText("Enter Email Address");

            Button submit = new Button("Submit");
            submit.setStyle(button_Style);
            submit.setOnMouseEntered(e -> submit.setStyle(button_hover));
            submit.setOnMouseExited(e -> submit.setStyle(button_Style));

            setting_scence.add(txt1, 0, 0);
            setting_scence.add(email, 1, 0);
            setting_scence.add(submit, 2, 2);
            
            submit.setOnAction((ActionEvent ev1) -> {
                System.out.println("\nEmail Address::"+email.getText());
                md.updateEmail(email.getText(),Controller.SESSION_usrname );
            });

        }

    }
    
    protected void ShowError(String title , String body){
         JOptionPane.showMessageDialog(null, body,title, JOptionPane.INFORMATION_MESSAGE);
    }

}
