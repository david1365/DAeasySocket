import ir.daak1365.daeasysocket.network.Protocol;
import ir.daak1365.daeasysocket.network.data.DAdata;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by david on 1/4/17.
 */
public class PubProtocl extends Protocol {

//    public void dataReceived(DAInputStream dataInput) throws IOException {
//        System.out.println("client " + dataInput.toString());
//
//        dataOutput.writeUTF("damet garm");
//    }

    protected void dataReceived(DAdata dataInput) throws IOException {
        this.client.write(dataInput.byteBuffer());

        System.out.println("get data-> " + dataInput.utf8());

        //this.client.write("damet garm");
    }

    public void connectionMade() {
        System.out.println("client Connect...");
    }

    public void connectionLost() {

    }

    public void connectionTimeout() {

    }
}
