package bgroup.stocktradingsystem.stsserver.socketutils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketCommon extends Thread {
    private Socket sd = null;
    private PrintWriter wtr = null;
    private String toSend;

    public SocketCommon(String mesg) {
        toSend = mesg;
        try {
            sd = new Socket("localhost", 8888);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            Scanner in =new Scanner(new InputStreamReader(sd.getInputStream()));
            wtr = new PrintWriter(sd.getOutputStream());

            wtr.println(toSend);
            wtr.flush();

            //close output stream
//            sd.shutdownOutput();

            while(in.hasNext()) {
                String line = in.nextLine();
                System.out.println("Received from server: " + line);
            }

//    		sd.shutdownInput();
//            while(sd != null);
            sd.close();
        } catch (Exception e) {
            wtr.close();
            try {
                sd.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
