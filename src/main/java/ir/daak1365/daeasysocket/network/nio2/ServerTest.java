package ir.daak1365.daeasysocket.network.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.*;

/**
 * Created by david on 7/31/17.
 */
class client implements Runnable{
    private AsynchronousSocketChannel clientChannel;

    public client(AsynchronousSocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    public void run() {
        if ((clientChannel != null) && (clientChannel.isOpen())) {
            while (true) {
                try {
                    System.out.println("gat data..." + clientChannel.getRemoteAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ByteBuffer buffer = ByteBuffer.allocate(32);
                Future<Integer> readResult  = clientChannel.read(buffer);

                // perform other computations

                try {
                    readResult.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                buffer.flip();
                Future<Integer> writeResult = clientChannel.write(buffer);

                // perform other computations

            }
        }
    }
}
public class ServerTest {
    public static void main(String[] args) {
        try {
//            int initialSize = Runtime.getRuntime().availableProcessors();
//            AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup
//                    .withCachedThreadPool(Executors.newCachedThreadPool(),
//                            initialSize);
            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();

            server.bind(new InetSocketAddress("127.0.0.1", 5050));

            while (true){
                System.out.println("listen...");

                Future<AsynchronousSocketChannel> acceptFuture = server.accept();

                AsynchronousSocketChannel clientChannel = null;
                try {
                    clientChannel = acceptFuture.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

               Executor executor = Executors.newCachedThreadPool();

                executor.execute(new client(clientChannel));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
