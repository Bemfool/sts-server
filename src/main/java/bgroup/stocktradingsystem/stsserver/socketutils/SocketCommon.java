package bgroup.stocktradingsystem.stsserver.socketutils;

import java.io.*;
import java.net.Socket;

public class SocketCommon {
    //创建Socket对象
    private static Socket socket;
//    static {
//        try {
//            String HOST = "localhost";
//            int PORT = 8888;
//            socket = new Socket(HOST, PORT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public SocketCommon() {}

    /**
     * Socket客户端
     */
    public static String doSocket(String msg) {
        try {
            //根据输入输出流和服务端连接
            OutputStream outputStream = socket.getOutputStream();//获取一个输出流，向服务端发送信息
            PrintWriter printWriter = new PrintWriter(outputStream);//将输出流包装成打印流
            printWriter.print(msg);
            printWriter.flush();
            socket.shutdownOutput();//关闭输出流

            InputStream inputStream = socket.getInputStream();//获取一个输入流，接收服务端的信息
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//包装成字符流，提高效率
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//缓冲区
            StringBuilder info = new StringBuilder();
            String temp;//临时变量
            while((temp = bufferedReader.readLine()) != null){
                info.append(temp);
                System.out.println("客户端接收服务端发送信息："+info);
            }
            //关闭相对应的资源
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            return info.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}