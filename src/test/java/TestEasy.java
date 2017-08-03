import ir.daak1365.daeasysocket.network.Service;

import java.io.IOException;

/**
 * Created by david on 1/4/17.
 */
public class TestEasy {
    public static void main(String[] args) throws IOException {
        Service service = new Service(5050, new PubFactory());

        service.start();
    }
}
