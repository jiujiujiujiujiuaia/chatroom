// tips 1 这个bug折磨了我好久 -- 当在一个stage中 点击一个按钮 相应另一个Stage时 成功的可以加载出新的视图 但是控制器和视图就是怎么样也不能连接起来
// 尽管我已经在build scene中设置好 可是还是不行 总是会显示“null.poninter错误”大概的意思就是没有找到与之相应的控制器 我再去研究下
// 更清楚的理解这个问题的本质
// tip 2 刚刚测试了一下 当第一次loader一个fxm的时候 它自己是知道自己的控制器是哪一个 当时再loader的时候 就没有一个相应的控制器与之绑定
// tip 3 刚刚又有新的发现 貌似除了第一个以外 都要 Control(自定义的control控制器的类) control = loader.getControler;
package Chat;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import edu.princeton.cs.algs4.StdOut;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;

public class LoginUiControl {
    @FXML
    private TextField password;
    @FXML
    private TextField username;

    private WarningUiControl warning ;
    private String CharacterString = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

    private ArrayList list = new ArrayList();

    private ChatApp chatapp;

    private void initialize() {

    }
    @FXML
    public void processLogin() throws IOException{
        String passwordStr = password.getText();
        String usernameStr = username.getText();

        if(passwordStr.length() != 0 && usernameStr.length() != 0 )
            if (judgeIsNumber(passwordStr) ) {
                //&& judgeIsCharacter(usernameStr)
                StdOut.println("进入登陆");
                chatapp.ShowChat(usernameStr);
            }
        else{
           processWarning("用户名或者密码不能为空");
        }
    }


    public boolean judgeIsNumber(String s) throws IOException{
        try{
            int number = Integer.parseInt(s);

            return true;
        }
        catch(Exception e){
            processWarning("密码格式错误");
            password.setText("");
            username.setText("");
            return false;
        }
    }
    public boolean judgeIsCharacter(String s) throws IOException{
        char[] str = s.toCharArray();
        for(char a :CharacterString.toCharArray()) list.add(a);
        for(char a :str)
        {
            if(!list.contains(a)) {
                processWarning("用户名格式错误");
                password.setText("");
                username.setText("");
                return false;}
        }
        return true;
    }

    public void processWarning(String s)throws IOException {

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("UiDesign/warning.fxml"));
//        loader.setLocation(LoginUiControl.class.getResource("warning.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ChatApp.class.getResource("UiDesign/warning.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Wrong");
        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        stage.setScene(scene);

        WarningUiControl control = loader.<WarningUiControl>getController();
        control.set(stage);
//        WarningUiControl control = loader.getController(); //如果不这样 例如label这样的成员变量就会null pointer
        control.Setwarning(s);
        stage.show();


    }
    public void set(ChatApp chatapp)
    {
        this.chatapp = chatapp;
    }

}
