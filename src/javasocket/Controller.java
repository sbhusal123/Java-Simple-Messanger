package javasocket;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class Controller {
    
    VBox msg_vbox = new VBox();

    @FXML
    ScrollPane scrlpane;

    @FXML
    TextField message;

    @FXML
    protected void sendMessage() {

        Label set_text = new Label();

        set_text.setText("Server Says: \n" + message.getText());
        

        set_text.setStyle("-fx-padding:10;-fx-margin:10;"
                + "-fx-background-color:teal;"
                + "    -fx-background-insets: 5;"
                + "-fx-font-size:15;"
                + "-fx-background-radius: 3;");
        set_text.setPrefSize(Double.MAX_VALUE, Double.compare(0, 500));
               set_text.setWrapText(true);
        set_text.setTextAlignment(TextAlignment.JUSTIFY);
        msg_vbox.getChildren().addAll(set_text);
        scrlpane.setContent(msg_vbox);
        message.setText("");

    }

    @FXML
    protected void check_key(KeyEvent ae) {

        if (ae.getCode().equals(KeyCode.ENTER)) {
            sendMessage();
        }
    }
}
