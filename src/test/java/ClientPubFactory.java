import ir.daak1365.daeasysocket.network.Factory;
import ir.daak1365.daeasysocket.network.Protocol;

/**
 * Created by david on 8/8/17.
 */
public class ClientPubFactory extends Factory {

    public Protocol buildProtocol() {

        return new ClientPubProtocol();

    }
}