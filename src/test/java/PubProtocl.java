import ir.daak1365.daeasysocket.network.Protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by david on 1/4/17.
 */
public class PubProtocl extends Protocol {

    public void dataReceived(byte[] data) throws IOException {
        System.out.println("client " + new String(data));

        DataOutputStream dataOutputStream = new DataOutputStream(this.outputStream);

        dataOutputStream.writeUTF("damet garm");
    }

    public void connectionMade() {
        System.out.println("client Connect...");
    }

    public void connectionLost() {

    }

    public void connectionTimeout() {

    }
}
