package ir.daak1365.daeasysocket;

/**
 * Created by david on 1/3/17.
 */

import java.io.IOException;

public class testrun {
    public static void main(String[] args) {
        int port = 5050;
        try {
            Thread t = new TestServerSocket(port);
            t.start();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}


