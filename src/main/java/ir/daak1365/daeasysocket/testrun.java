package ir.daak1365.daeasysocket;

/**
 * Created by david on 1/3/17.
 */

import ir.daak1365.daeasysocket.exception.InputParameterException;
import ir.daak1365.daeasysocket.test.PatternTest;

import java.io.IOException;

public class testrun {
    public static void main(String[] args) throws InputParameterException {
        //System.out.println(PatternTest.parameter("port:1221"));


        int port = 5050;
        try {
            Thread t = new TestServerSocket(port);
            t.start();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}


