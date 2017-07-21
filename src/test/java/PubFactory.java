import ir.daak1365.daeasysocket.network.Factory;
import ir.daak1365.daeasysocket.network.Protocol;

import java.io.IOException;

/**
 * Created by david on 1/4/17.
 */
public class PubFactory extends Factory{

    public Protocol buildProtocol() {

        return new PubProtocl();

    }

    public void listening() {
        System.out.println("on listening...");
    }
}
