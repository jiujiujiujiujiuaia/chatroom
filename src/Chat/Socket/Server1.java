package Chat.Socket;
/**
 * Created by 87057 on 2018/3/20.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server1 {
    public static ArrayList<Socket> socketpool = new ArrayList<>();
    public  static void serve()throws IOException{
        ServerSocket server = new ServerSocket(20006);
        Socket client = null;
        boolean f = true;
        while(f){
            //等待客户端的连接，如果没有获取连接
            client = server.accept();
            socketpool.add(client);
            System.out.println("与客户端连接成功！");
            //为每个客户端连接开启一个线程
            new Thread(new ServerThread(client)).start();
        }

    }
    public static void main(String[] args) throws IOException{
       Server1.serve();
    }
}
