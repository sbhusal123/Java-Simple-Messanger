package javasocket;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class Controller {

    @FXML
    VBox converstation;

    @FXML
    TextField message;

    @FXML
    protected void sendMessage() {

        Label surya = new Label();

        surya.setText("Server Says: \n" + message.getText());

        surya.setStyle("-fx-padding:10;-fx-margin:10;"
                + "-fx-background-color:teal;"
                + "    -fx-background-insets: 5;"
                + "-fx-font-size:15;"
                + "-fx-background-radius: 3;");
        surya.setPrefSize(Double.MAX_VALUE, Double.compare(0, 500));
               surya.setWrapText(true);
        surya.setTextAlignment(TextAlignment.JUSTIFY);
        converstation.getChildren().add(surya);

        message.setText("");

    }

    @FXML
    protected void check_key(KeyEvent ae) {

        if (ae.getCode().equals(KeyCode.ENTER)) {
            sendMessage();
        }

    }
}
