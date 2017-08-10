package ir.daak1365.daeasysocket.network.data;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.spi.AsynchronousChannelProvider;

/**
 * Created by david on 8/10/17.
 */
public abstract class DAAsynchronousSocketChannel extends AsynchronousSocketChannel
{

    /**
     * Initializes a new instance of this class.
     *
     * @param provider The provider that created this channel
     */
    protected DAAsynchronousSocketChannel(AsynchronousChannelProvider provider) {
        super(provider);
    }



    public void write(String message){
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());

        this.write(buffer);
    }
}
