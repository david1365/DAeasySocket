package ir.daak1365.daeasysocket.network;

import java.io.DataInputStream;
import java.io.InputStream;

/**
 * Created by david on 7/21/17.
 */
public class DAInputStream extends DataInputStream {
    /**
     * Creates a DataInputStream that uses the specified
     * underlying InputStream.
     *
     * @param in the specified input stream
     */
    public DAInputStream(InputStream in) {
        super(in);
    }
}
