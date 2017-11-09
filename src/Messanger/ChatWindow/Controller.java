package Messanger.ChatWindow;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import Messanger.Login.Login;
import java.io.IOException;
import Messanger.Settings.Settings;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    Settings set = new Settings();

    VBox msg_vbox = new VBox();

    @FXML
    ScrollPane scrlpane;

    @FXML
    TextField message;

    @FXML
    protected void Settings() {
        try {
            set.loadView();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    protected void Logout() throws IOException {
        Login lgin = new Login();
        lgin.loadView();
        ChatWindow.cW.close();
    }

    @FXML
    protected void sendMessage() {
         //new label text with message.
        Label set_text = new Label();
        set_text.setText(Messanger.Login.Controller.SESSION_usrname + " Says: \n" + message.getText());
        set_text.setStyle("-fx-padding:10;"
                + "-fx-width:100%;"
                + "-fx-background-color:teal;"
                + "    -fx-background-insets: 5;"
                + "-fx-font-size:15;"
                + "-fx-background-radius: 3;");

        set_text.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        set_text.setWrapText(true);
        set_text.setTextAlignment(TextAlignment.JUSTIFY);
        set_text.setPrefWidth(600);

        //VBox wrapper
        msg_vbox.getChildren().addAll(set_text);
        msg_vbox.setPrefWidth(600);

        //Further wrapped by ScrollPane
        scrlpane.fitToHeightProperty();
        scrlpane.setContent(msg_vbox);
        scrlpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrlpane.vvalueProperty().bind(msg_vbox.heightProperty()); //sets the scroll view to new element.
        message.setText("");
    }

    @FXML
    protected void check_key(KeyEvent ae) {
        if (ae.getCode().equals(KeyCode.ENTER)) {
            sendMessage();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrlpane.setStyle("-fx-background:#32AED8");
        scrlpane.setPrefHeight(300);
    }

}
