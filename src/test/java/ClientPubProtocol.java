import ir.daak1365.daeasysocket.network.Protocol;
import ir.daak1365.daeasysocket.network.data.DAdata;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * Created by david on 8/8/17.
 */
public class ClientPubProtocol extends Protocol {
    private Scanner scanner = new Scanner(System.in);

    protected void dataReceived(DAdata dataInput) throws IOException {
        System.out.println("Server says-> " + dataInput.utf8());

        String input = scanner.nextLine();
        ByteBuffer buffer = ByteBuffer.wrap(input.getBytes());

        this.client.write(buffer);
    }

    public void connectionMade() {
        System.out.println("client Connect...");
        String input = scanner.nextLine();

        client.write(input);
    }

    public void connectionLost() {

    }

    public void connectionTimeout() {

    }
}
