package test;

import ir.daak1365.daeasysocket.exception.InputParameterException;
import static ir.daak1365.daeasysocket.exception.MessageExceptions.*;

import java.util.HashMap;


/**
 * Created by david on 1/6/17.
 */
public class PatternTest {
    private static final String parameterPattern = "(.+):(.+)";
    public static HashMap<String, String> parameter(String string) throws InputParameterException {
        if(!string.matches(parameterPattern)){
            throw new InputParameterException(inputParameterException);
        }

        String[] paramValues =  string.split(":");

        HashMap<String, String> parameter = new HashMap<String, String>();
        parameter.put(paramValues[0], paramValues[1]);

        return parameter;
    }
}
