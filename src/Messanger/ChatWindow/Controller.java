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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javax.swing.JOptionPane;

class GUIupdater implements Runnable {

    Controller ctrl;
    Thread mainThread;
    boolean mainThreadStatus = false;
    
    

    GUIupdater(Controller ctrl) {
        mainThreadStatus = true;
        this.ctrl = ctrl;
    }
    

    @Override
    public void run() {
        while (true || mainThreadStatus) {
            try {
                // do long-running operation
                List<Node> newContent = ctrl.refreshedContent();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // there should be no need to do this over and over again
                        // you should move it outside of the task
                        ctrl.msg_vbox.setPrefWidth(600);
                        //scrlpane.fitToHeightProperty(); // does nothing anyway...
                        ctrl.scrlpane.setContent(ctrl.msg_vbox);
                        ctrl.scrlpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                        ctrl.scrlpane.vvalueProperty().bind(ctrl.msg_vbox.heightProperty()); //probably won't work the intended way...

                        // update ui
                        ctrl.msg_vbox.getChildren().setAll(newContent);
                        
                    }

                });
                // do more long-running operations
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("asd");
            } catch (SQLException ex) {
                Logger.getLogger(GUIupdater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

public class Controller implements Initializable {
    
    Thread t1 = null;

    Settings set = new Settings();

    VBox msg_vbox = new VBox();

    @FXML
    ScrollPane scrlpane;

    @FXML
    TextField message;

    protected Model md;

    public Controller() throws SQLException {
        this.md = new Model();
    }

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

    List<Node> refreshedContent() throws SQLException {
        List<Node> result = new ArrayList<>();

        ResultSet messageArray = md.getMessages();

        while (messageArray.next()) {
            Label set_text = new Label();
            set_text.setText(messageArray.getString("username") + " Says: \n" + messageArray.getString("message"));
            set_text.setStyle("-fx-padding:10;"
                    + "-fx-width:100%;"
                    + "-fx-background-color:teal;"
                    + "    -fx-background-insets: 5;"
                    + "-fx-font-size:15;"
                    + "-fx-background-radius: 3;");

            set_text.setPrefSize(600, Region.USE_COMPUTED_SIZE);
            set_text.setWrapText(true);
            set_text.setTextAlignment(TextAlignment.JUSTIFY);

            result.add(set_text);
        }

        return result;
    }
    

    @FXML
    protected void sendMessage() {
        //new label text with message.
        if (md.addMessage(Messanger.Login.Controller.SESSION_usrname, message.getText())) {
            message.setText("");
        }
    }

    @FXML
    protected void check_key(KeyEvent ae) throws SQLException {
        if (ae.getCode().equals(KeyCode.ENTER)) {
            if (md.addMessage(Messanger.Login.Controller.SESSION_usrname, message.getText())) {
                message.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Message Sending failed \n "
                        + "Please Check Your Internet Connection", "Error ", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrlpane.setStyle("-fx-background:#32AED8");
        scrlpane.setPrefHeight(300);

        t1 = new Thread(new GUIupdater(this));
        t1.start();
    }
}
