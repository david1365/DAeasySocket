/**
 * Created by david on 1/3/17.
 */
// File Name GreetingClient.java
import ir.daak1365.daeasysocket.network.Service;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TestClientSocket {

    public static void main(String [] args) throws IOException {
//        String serverName = "127.0.0.1";
//        int port = 5050;
//
//        System.out.println("Connecting to " + serverName + " on port " + port);
//        Socket client = new Socket(serverName, port);
//
//        while (true) {
//            try {
//                System.out.println("Just connected to " + client.getRemoteSocketAddress());
//                OutputStream outToServer = client.getOutputStream();
//
//                DataOutputStream out = new DataOutputStream(outToServer);
//
//                Scanner scanner = new Scanner(System.in);
//                String input = scanner.nextLine();
//
//                //out.writeUTF("Hello from " + client.getLocalSocketAddress());
//                out.writeUTF(input);
//
//
//                InputStream inFromServer = client.getInputStream();
//                DataInputStream in = new DataInputStream(inFromServer);
//
//                System.out.println("Server says " + in.readUTF());
//                //client.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        Service service = new Service("localhost", 5050, new ClientPubFactory());
        service.connectTCP();
    }
}
