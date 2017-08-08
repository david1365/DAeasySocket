package ir.daak1365.daeasysocket.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by david on 1/4/17.
 */
public final class Service extends InetSocketAddress implements Runnable {
    private final static String SERVER = "SERVER";
    private final static String CLIENT = "CLIENT";

    private AsynchronousServerSocketChannel server;
    private AsynchronousSocketChannel client;
    private Factory factory;
    private Executor executor;
    private String serviceType = SERVER;
//    private int port;
//    private int backlog;
//    private InetAddress address;
    private boolean hasStopped = false;

//    private int timeout = 0;
//
//    private final int FROM_TWO_PARAMETER = 2;
//    private final int FROM_THREE_PARAMETER = 3;
//    private final int FROM_FOUR_PARAMETER = 4;

    private static int parameterCount = 0;
    private static boolean usedSSL = false;

    public Service(int port, Factory factory) {
        super(port);
        this.factory = factory;
    }

    public Service(InetAddress addr, int port, Factory factory) {
        super(addr, port);
        this.factory = factory;
    }

    public Service(String hostname, int port, Factory factory) {
        super(hostname, port);
        this.factory = factory;
    }
//    public Service(Factory factory, int port) {
//        this.factory = factory;
//        this.port = port;
//
//        this.parameterCount = FROM_TWO_PARAMETER;
//    }
//
//    public Service(Factory factory, int port, int backlog) {
//        this(factory, port);
//        this.backlog = backlog;
//
//        this.parameterCount = FROM_THREE_PARAMETER;
//    }
//
//    public Service(Factory factory, int port, int backlog, InetAddress address) {
//        this(factory, port, backlog);
//        this.address = address;
//
//        this.parameterCount = FROM_FOUR_PARAMETER;
//    }

    public void useSSL(boolean usedSSl){
        this.usedSSL = usedSSl;
    }


    private void openPort() throws IOException {
        if(serviceType == SERVER) {
            server = AsynchronousServerSocketChannel.open(AsynchronousSocketChannel client);
            server.bind(this);

            this.factory.listening();
        }
        else {
            client = AsynchronousSocketChannel.open();
        }

//        switch (parameterCount){
//            case FROM_TWO_PARAMETER:
//                server.bind(new InetSocketAddress(port));
//                break;
//            case FROM_THREE_PARAMETER:
//                server.bind(new InetSocketAddress(port));
//                server = new  ServerSocket(port, backlog);
//                break;
//            case FROM_FOUR_PARAMETER:
//                server = new  ServerSocket(port, backlog, address);
//                break;
//        }



//        server.setSoTimeout(timeout);

    }

//    public int getTimeout() {
//        return timeout;
//    }

//    public void setTimeout(int timeout) {
//        this.timeout = timeout;
//    }

    private void create(){
        //TODO:change this line with executer
        new Thread(this).start();
    }

    public void start() throws IOException {
        serviceType = SERVER;

        create();
    }

    private Protocol protocol(AsynchronousSocketChannel client){
        Protocol protocol = this.factory.buildProtocol();
        protocol.setClient(client);

        return protocol;
    }

    private void server(){
        try {
            openPort();

            executor = Executors.newCachedThreadPool();

            while(!hasStopped) {
                //Socket newSocket = this.server.accept();
                Future<AsynchronousSocketChannel> acceptFuture = server.accept();

                AsynchronousSocketChannel client = null;
                try {
                    client = acceptFuture.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                executor.execute(protocol(client));

//                new Thread(protocol).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //-------------------------------------
    public void connectTCP(){
        serviceType = CLIENT;

        create();
    }

    private void client(){
        try {
            openPort();

            Future<Void> future = client.connect(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        if(serviceType == SERVER){
            server();
        }
        else {
            client();
        }
    }
}

