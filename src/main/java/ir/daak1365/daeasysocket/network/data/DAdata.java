package ir.daak1365.daeasysocket.network.data;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Created by david on 8/8/17.
 */
public class DAdata {
    private ByteBuffer buffer;

    public DAdata(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public final ByteBuffer byteBuffer() {
        return buffer;
    }

    public final String string() {
        return new String(buffer.array()).trim();
    }

    public final String string(String charsetName) {
        try {
            return new String(buffer.array(), charsetName).trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public final String utf8() {
        return string(StandardCharsets.UTF_8.name());
    }

    public final byte[] array() {
        return buffer.array();
    }

    //-------------------------------------
    public static ByteBuffer byteBuffer(String message, String charsetName) throws UnsupportedEncodingException {
        return ByteBuffer.wrap(message.getBytes(charsetName));
    }

    public static ByteBuffer byteBuffer(String message) {
        return ByteBuffer.wrap(message.getBytes());
    }
}
