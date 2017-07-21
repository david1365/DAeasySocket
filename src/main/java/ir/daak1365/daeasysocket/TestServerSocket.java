package ir.daak1365.daeasysocket;

/**
 * Created by david on 1/3/17.
 */

// File Name GreetingServer.java
import java.net.*;
import java.io.*;

public class TestServerSocket extends Thread {
    private ServerSocket serverSocket;
    private  Socket clientServerSocket;
    private   DataInputStream in;
    private  DataOutputStream out;

    public TestServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);

        System.out.println("Waiting for client on port " +
                serverSocket.getLocalPort() + "...");
        clientServerSocket = serverSocket.accept();

        System.out.println("Just connected to " + clientServerSocket.getRemoteSocketAddress());
        in = new DataInputStream(clientServerSocket.getInputStream());
        out = new DataOutputStream(clientServerSocket.getOutputStream());
    }

    public void run() {
        while(true) {
            try {
                System.out.println(in.readUTF());
                out.writeUTF("Thank you for connecting to " + clientServerSocket.getLocalSocketAddress());
                //server.close();

            }catch(SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}