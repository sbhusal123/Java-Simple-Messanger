package javasocket;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class Controller {

    @FXML
    ScrollPane messages;

    VBox messages_vbox = new VBox();
    
    @FXML
    TextField message;

    @FXML
    protected void sendMessage() {

        Label set_text = new Label();

        set_text.setText("Server Says: \n" + message.getText());

        set_text.setStyle("-fx-padding:10;-fx-margin:10;"
                + "-fx-background-color:teal;"
                + "-fx-background-insets: 5;"
                + "-fx-font-size:15;"
                + "-fx-background-radius: 3;");
        set_text.setPrefSize(Double.MAX_VALUE, Double.compare(0, 500));
               set_text.setWrapText(true);
        set_text.setTextAlignment(TextAlignment.JUSTIFY);
        
        
        //vbox
        messages_vbox.getChildren().add(set_text);
        messages_vbox.setStyle("-fx-background-color:teal");
        
        messages.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        messages.setHbarPolicy(ScrollBarPolicy.NEVER);
        
        //scrollpane
        messages.setContent(messages_vbox);
        message.setText("");

    }

    @FXML
    protected void check_key(KeyEvent ae) {

        if (ae.getCode().equals(KeyCode.ENTER)) {
            sendMessage();
        }
    }
}
