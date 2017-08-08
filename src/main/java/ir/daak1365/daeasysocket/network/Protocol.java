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

//    public Socket getSocket() {
//        return socket;
//    }

//    public void setSocket(Socket socket) throws IOException {
//        this.socket = socket;
//        this.dataOutput = new DAOutputStream(this.socket.getOutputStream());
//
//        connectionMade();
//    }

//    public void dataReceived(String data) throws IOException{
//
//    }
//
//    public void dataReceived(byte[] data) throws IOException{
//
//    }

//    protected abstract void dataReceived(DAInputStream dataInput) throws IOException;

    protected abstract void dataReceived(DAdata dataInput) throws IOException;

    protected abstract void connectionMade();
    protected abstract void connectionLost();
    protected abstract void connectionTimeout();


    public void run() {
        if ((client != null) && (client.isOpen())) {
            ByteBuffer buffer = null;

            while (true) {
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

//        try {
//            System.out.println(in.readUTF());
//            out.writeUTF("Thank you for connecting to " + clientServerSocket.getLocalSocketAddress());
//            //server.close();
//
//        }catch(SocketTimeoutException s) {
//            System.out.println("Socket timed out!");
//            break;
//        }catch(IOException e) {
//            e.printStackTrace();
//            break;
//        }

//        try {
//
//            while (true){
//
//                DataInputStream  dataInput = new DataInputStream(socket.getInputStream());

//                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    //                if(inputStream.read() <= -1){
    //                    connectionLost();
    //                    break;
    //                }

               // DAInputStream daInputStream = new DAInputStream(dataInput);
                //byte[] data2 = new byte[in.available()];
                //in.readLine();
//                if (dataInput.available() <= 0){
//                    //try {
//                        synchronized (this){
//                            //this.wait();
//                            //Thread.sleep(1000);
//                            Thread.yield();
//                        }
//
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
//                }
//                else{
//                    byte[] data = new byte[dataInput.available()];
//                    //dataInput.read(data);
//                    dataInput.readFully(data);
//                    // String stringData = new String(data);
//
//                    dataReceived(new DAInputStream(new ByteArrayInputStream(data)));
////                }
//            }
//
//            }catch(SocketTimeoutException s) {
//                connectionTimeout();
//                s.printStackTrace();
//            } catch (IOException e) {
//                connectionLost();
//                e.printStackTrace();
//            }
    }
}
