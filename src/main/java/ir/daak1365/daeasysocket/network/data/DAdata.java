package ir.daak1365.daeasysocket.network.data;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.HeapByteBuffer;

/**
 * Created by david on 8/8/17.
 */
public  class DAdata extends HeapByteBuffer {
   private static final String UTF8 = "UTF-8";

    private ByteBuffer buffer;

    public DAdata(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public final ByteBuffer byteBuffer() {
        return buffer;
    }

    public final String string() {
        return new String(buffer.array());
    }

    public final String string(String charsetName) {
        try {
            return new String(buffer.array(), charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public final String utf8() {
        return string(UTF8);
    }

    public final byte[] array() {
        return buffer.array();
    }

    public ByteBuffer wrapByteBuffer(String message, String charsetName) throws UnsupportedEncodingException {
        return ByteBuffer.wrap(message.getBytes(charsetName));
    }

    public ByteBuffer wrapByteBuffer(String message){
        return ByteBuffer.wrap(message.getBytes());
    }
}
