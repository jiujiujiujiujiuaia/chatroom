package Chat;
import edu.princeton.cs.algs4.StdOut;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ChatApp extends Application {
    private Stage primaryStage;
    private ObservableList<Friend> personData = FXCollections.observableArrayList();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("chatroom");
        ShowLogin();
    }

    public ChatApp(){
        personData.add(new Friend("test1"));
        personData.add(new Friend("test2"));
    }
    public  ObservableList<Friend> getPersonData(){
        return personData;
    }
    public void ShowLogin() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ChatApp.class.getResource("UiDesign/ui.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);

        LoginUiControl control = loader.getController();
        control.set(this);


        primaryStage.setScene(scene);
        primaryStage.show();


    }
        public void ShowChat(String username) throws IOException{

            FXMLLoader loader = new FXMLLoader();


            loader.setLocation(ChatApp.class.getResource("UiDesign/chat.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            ChatControl control = loader.getController();

            control.setUsername(username,this);

            primaryStage.setScene(scene);
            StdOut.println("进入好友列表");
            primaryStage.show();

    }




    }






