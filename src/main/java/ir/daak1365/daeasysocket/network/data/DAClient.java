package ir.daak1365.daeasysocket.network.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.NetworkChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * Created by david on 8/10/17.
 */
public class DAClient implements AsynchronousByteChannel, NetworkChannel {
    protected AsynchronousSocketChannel client;

    public DAClient(AsynchronousSocketChannel client) {
        this.client = client;
    }

    @Override
    public <A> void read(ByteBuffer dst, A attachment, CompletionHandler<Integer, ? super A> handler) {
        client.read(dst, attachment, handler);
    }

    @Override
    public Future<Integer> read(ByteBuffer dst) {
        return client.read(dst);
    }

    @Override
    public <A> void write(ByteBuffer src, A attachment, CompletionHandler<Integer, ? super A> handler) {
        client.write(src, attachment, handler);
    }

    @Override
    public Future<Integer> write(ByteBuffer buffer){
        return client.write(buffer);
    }

    public Future<Integer> write(String message){
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());

        return this.write(buffer);
    }

    public Future<Integer> writeUTF8(String message) {
        ByteBuffer buffer = null;
        try {
            buffer = DAdata.byteBuffer(message, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return this.write(buffer);
    }

    public Future<Integer> write(String message, String charsetName) throws UnsupportedEncodingException {
        return this.write(DAdata.byteBuffer(message, StandardCharsets.UTF_8.name()));
    }

    @Override
    public void close() throws IOException {
        client.close();
    }

    @Override
    public boolean isOpen(){
        return client.isOpen();
    }

    @Override
    public NetworkChannel bind(SocketAddress local) throws IOException {
        return client.bind(local);
    }

    @Override
    public SocketAddress getLocalAddress() throws IOException {
        return client.getLocalAddress();
    }

    @Override
    public <T> NetworkChannel setOption(SocketOption<T> name, T value) throws IOException {
        return client.setOption(name, value);
    }

    @Override
    public <T> T getOption(SocketOption<T> name) throws IOException {
        return client.getOption(name);
    }

    @Override
    public Set<SocketOption<?>> supportedOptions() {
        return client.supportedOptions();
    }
}
