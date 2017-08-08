package ir.daak1365.daeasysocket.network;

import ir.daak1365.daeasysocket.network.data.DAdata;
import ir.daak1365.daeasysocket.network.data.DAOutputStream;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.*;

/**
 * Created by david on 1/4/17.
 */
public abstract class Protocol implements Runnable {
    protected AsynchronousSocketChannel client;
    protected DAOutputStream dataOutput;

    public AsynchronousSocketChannel getClient() {
        return client;
    }

    public void setClient(AsynchronousSocketChannel client) {
        this.client = client;

        connectionMade();
    }

    protected abstract void dataReceived(DAdata dataInput) throws IOException;

    protected abstract void connectionMade();
    protected abstract void connectionLost();
    protected abstract void connectionTimeout();


    public void run() {
            ByteBuffer buffer = null;
            while (true) {
                if ((client == null) || (!client.isOpen())) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                buffer = ByteBuffer.allocate(32);
                Future readResult = client.read(buffer);

                try {
                    readResult.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                buffer.flip();

                try {
                    dataReceived(new DAdata(buffer));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
