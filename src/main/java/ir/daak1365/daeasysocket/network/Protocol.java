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

    public void setClient(AsynchronousSocketChannel client) {
        this.client = client;

        connectionMade();
    }

    protected abstract void dataReceived(DAdata dataInput) throws IOException;

    protected void connectionMade(){}
    protected void connectionLost(){}
    protected void connectionTimeout(){}

    private void close(){
        try {
            client.close();
            connectionLost();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        ByteBuffer buffer = null;
        if ((client == null) || (client.isOpen())) {
            while (true) {
                //todo: add custom allocate
                buffer = ByteBuffer.allocate(32);
                Future<Integer> readResult = client.read(buffer);

                try {
                    Integer resultStatus = readResult.get();
                    if (resultStatus == -1){
                        close();
                        return;
                    }

                } catch (InterruptedException | ExecutionException e) {
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
}
