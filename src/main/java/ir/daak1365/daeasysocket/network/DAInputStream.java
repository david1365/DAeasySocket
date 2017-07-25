package ir.daak1365.daeasysocket.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by david on 7/21/17.
 */
public class DAInputStream extends DataInputStream {
    public DAInputStream(InputStream in) {
        super(in);
    }

    public byte[] toByte(){
        byte[] data = null;

        try {
            data = new byte[this.available()];
            this.readFully(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public String toString() {
        return new String(toByte());
    }


}
