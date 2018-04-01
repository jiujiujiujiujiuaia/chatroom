package Chat.Socket; /**
 * Created by 87057 on 2018/3/20.
 */
import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 该类为多线程类，用于服务端
 */
public class ServerThread implements Runnable {

    private Socket client = null;
    public ServerThread(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        try{
            StdOut.println("线程开始");
            //获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag =true;
            while(flag){
                //接收从客户端发送过来的数据
                String str =  buf.readLine();
                StdOut.println("服务器接受客户端的数据为"+str);
                String[] message = str.split("@");
                if(str == null || "".equals(str)){

                }else{
                    if("bye".equals(message[1])){

                    }
//                    else if(Server1.socketpool.contains(message[2]))
                    else{
                        Socket s = Server1.socketpool.get(1);
                        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
                        pw.println(message[1]+"@"+message[2]);
                        pw.flush();

                            //将接收到的字符串前面加上echo，发送到对应的客户端
//                        StdOut.println("echo:" + str);
//                        out.println("echo:" + str);
                    }
                }
            }

            out.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
