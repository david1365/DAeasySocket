package ir.daak1365.daeasysocket.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Created by david on 1/4/17.
 */
public abstract class Factory {
    public abstract Protocol buildProtocol();
    public void listening(){}
}
