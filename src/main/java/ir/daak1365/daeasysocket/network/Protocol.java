package ir.daak1365.daeasysocket.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by david on 1/4/17.
 */
public abstract class Protocol implements Runnable {
    protected Socket socket;
    protected OutputStream outputStream;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = this.socket.getOutputStream();

        connectionMade();
    }

    public void dataReceived(String data) throws IOException{

    }

    public void dataReceived(byte[] data) throws IOException{

    }

    public void dataReceived(DAInputStream data) throws IOException{

    }

    public abstract void connectionMade();
    public abstract void connectionLost();
    public abstract void connectionTimeout();


    public void run() {
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

        while (true){
            InputStream inputStream = null;

            try {
                inputStream = socket.getInputStream();
                DataInputStream in = new DataInputStream(inputStream);

//                if(inputStream.read() <= -1){
//                    connectionLost();
//                    break;
//                }

                byte[] data = new byte[in.available()];
                in.readFully(data);

               // String stringData = new String(data);

                dataReceived(data);
            }catch(SocketTimeoutException s) {
                connectionTimeout();
                break;
            } catch (IOException e) {
                connectionLost();
                break;
            }
        }
    }
}