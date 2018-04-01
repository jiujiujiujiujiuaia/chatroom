package Chat.Socket; /**
 * Created by 87057 on 2018/3/20.
 */
import Chat.ChatControl;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Client1 {

    private PrintStream out;
    private  BufferedReader buf;
    private Socket client;
    private ChatControl chat;
    public  Client1(String hostname, Integer port, ChatControl chat) throws IOException{
        Socket client = new Socket(hostname, port);
        this.client = client;
        this.chat = chat;
        new ClientThread().start();
    }

    public void sendMessage(String str) throws IOException{
        //获取Socket的输出流，用来发送数据到服务端
        out = new PrintStream(client.getOutputStream());
        //获取Socket的输入流，用来接收从服务端发送过来的数据

        boolean flag = true;
        while(flag){
            String[] message = str.split("@");
            //发送数据到服务端
            out.println(str);
            StdOut.println("客户端正在向服务器发送数据："+str);
            flag = false;
//            if("0x01".equals(message[0])){
//                flag = false;
//                client.close();
//
//            }else{
//                try{
//                    //从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
//                    String echo = buf.readLine();
//                    if(echo !=null){
//                        chat.ProcessGetMessage(echo);
//                    }
//                    flag = false;
//                }catch(SocketTimeoutException e){
//                    System.out.println("Time out, No response");
//                }
//            }
        }


    }
    public void connect() throws IOException{
        Socket client = new Socket("172.20.10.2", 20000);
        client.setSoTimeout(10000);


    }

    class ClientThread extends Thread  {
        //这个地方记录一下 第一 ：为什么这个地方强行让他不断的死循环 但是没有影响到程序
        //                  第二： 如果按照一个线程当他执行完他的代码之后就结束了线程 那为什么前面客户端和服务器的线程一直都没有断
        @Override
        public void run() {
            while (true) {
                try {
                    buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String echo = buf.readLine();
                    StdOut.println("从服务端接受到了数据" + echo);
                    if (echo != null && echo != "") {
                        chat.ProcessGetMessage(echo);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }








    public static void main(String[] args) throws IOException {


    }
}
