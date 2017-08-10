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
        Service service = new Service("localhost", 5050, new ClientPubFactory());
        service.connectTCP();
    }
}
