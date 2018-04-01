package Chat;

import Chat.Socket.Client1;
import edu.princeton.cs.algs4.StdOut;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Created by 87057 on 2018/3/26.
 */
public class ChatControl {
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField ;
    @FXML
    private TableView<Friend> tableView;
    @FXML
    private TableColumn<Friend,String> column;
    private ChatApp chatApp;
    private Client1 client;
    private String username;
    private String sendname;
    public void initialize() throws IOException{
        Client1 client = new Client1("172.20.10.2",20006,this);
        this.client = client;
        StdOut.println("连接成功");
        column.setCellValueFactory(cellData -> cellData.getValue().friendnameProperty());
        tableView.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> this.sendname = newValue.getfriendname());
    }
    public void ProcessGetMessage(String message){
        String[] arraymessage = message.split("@");
        textArea.appendText(arraymessage[1]+":"+arraymessage[0]);
        textArea.appendText("\n");
    }

    public void ProcessSendButton() throws  IOException{
//        Client1 client = new Client1("172.20.10.2",20006,this);
//        this.client = client;
        String message = textField.getText();
        textField.setText("");
        String sendtosb =username+"@"+message+"@"+sendname;
        client.sendMessage(sendtosb);
        textArea.appendText(username+" : "+message);
        textArea.appendText("\n");

    }


   public void setUsername(String username,ChatApp chatapp){
       this.username = username;
       this.chatApp = chatapp;
       tableView.setItems(chatapp.getPersonData());

   }

}
