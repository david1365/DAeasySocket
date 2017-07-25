package ir.daak1365.daeasysocket.network;

import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;

/**
 * Created by david on 7/25/17.
 */
public class DAOutputStream extends DataOutputStream{
    public DAOutputStream(OutputStream out) {
        super(out);
    }
}
