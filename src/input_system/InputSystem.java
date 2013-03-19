package input_system;

import engine.utils.Node;
import java.io.EOFException;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 * @author Ilias Trichopoulos <itrichop@csd.auth.gr>
 */
public interface InputSystem {
    
    /**
     * Reads the next interval and returns the point
     * @return the point
     * @throws EOFException 
     */
    public Node nextInterval() throws EOFException;
    
}
