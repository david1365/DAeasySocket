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

    private boolean hasStopped = false;

//    private int timeout = 0;
//

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

//    public void useSSL(boolean usedSSl){
//        this.usedSSL = usedSSl;
//    }


    private void openPort() throws IOException {
        if(serviceType == SERVER) {
            server = AsynchronousServerSocketChannel.open();
            server.bind(this);

            this.factory.listening();
        }
        else {
            client = AsynchronousSocketChannel.open();
        }

    }

//    public int getTimeout() {
//        return timeout;
//    }

//    public void setTimeout(int timeout) {
//        this.timeout = timeout;
//    }

    private void create(Runnable service){
        //TODO:change this line with executer
        new Thread(service).start();
    }

    public void start() throws IOException {
        serviceType = SERVER;

        create(this);
    }

    private Protocol protocol(AsynchronousSocketChannel client){
        Protocol protocol = this.factory.buildProtocol();
        protocol.setClient(client);

        return protocol;
    }

    private void serverRun(){
        try {
            openPort();

            executor = Executors.newCachedThreadPool();

            while(!hasStopped) {
                //Socket newSocket = this.serverRun.accept();
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

        try {

            openPort();

            client.connect(this);

            create(protocol(client));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//-------------------------
    public void run() {
            serverRun();
    }
}

