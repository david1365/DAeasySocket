package ir.daak1365.daeasysocket.network;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by david on 1/4/17.
 */
public final class Service implements Runnable{
    private ServerSocket serverSocket;
    private Factory factory;
    private int port;
    private int backlog;
    private InetAddress address;
    private boolean hasStopped = false;

    private int timeout = 0;

    private final int FROM_TWO_PARAMETER = 2;
    private final int FROM_THREE_PARAMETER = 3;
    private final int FROM_FOUR_PARAMETER = 4;

    private static int parameterCount = 0;
    private static boolean usedSSL = false;


    public Service(Factory factory, int port) {
        this.factory = factory;
        this.port = port;

        this.parameterCount = FROM_TWO_PARAMETER;
    }

    public Service(Factory factory, int port, int backlog) {
        this(factory, port);
        this.backlog = backlog;

        this.parameterCount = FROM_THREE_PARAMETER;
    }

    public Service(Factory factory, int port, int backlog, InetAddress address) {
        this(factory, port, backlog);
        this.address = address;

        this.parameterCount = FROM_FOUR_PARAMETER;
    }

    public void useSSL(boolean usedSSl){
        this.usedSSL = usedSSl;
    }


    private void openPort() throws IOException {
        switch (parameterCount){
            case FROM_TWO_PARAMETER:
                serverSocket = new ServerSocket(port);
                break;
            case FROM_THREE_PARAMETER:
                serverSocket = new  ServerSocket(port, backlog);
                break;
            case FROM_FOUR_PARAMETER:
                serverSocket = new  ServerSocket(port, backlog, address);
                break;
        }



        serverSocket.setSoTimeout(timeout);

        this.factory.listening();
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void start() throws IOException {
        new Thread(this).start();
    }

    public void run() {
        try {
            openPort();

            while(!hasStopped) {
                Socket newSocket = this.serverSocket.accept();

                Protocol protocol =  this.factory.buildProtocol();
                protocol.setSocket(newSocket);

                new Thread(protocol).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

