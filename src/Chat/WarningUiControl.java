package Chat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by 87057 on 2018/3/22.
 */
public class WarningUiControl {
    @FXML
    private Label label;

    private Stage stage;

    public void set(Stage stage){
        this.stage = stage;
    }


    public void Setwarning(String s){
        label.setText(s);

    }
    @FXML
    public void processbuttonOK(){
        stage.close();
    }
}
