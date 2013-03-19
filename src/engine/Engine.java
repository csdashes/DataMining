package engine;

import engine.utils.Node;
import java.io.EOFException;

/**
 * Interface that represents the calculation engine.
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 * @author Ilias Trichopoulos <itrichop@csd.auth.gr>
 */
public interface Engine {
    
    /**
     * The main calculation algorithm
     * @return a point object
     * @throws EOFException 
     */
    public Node decay() throws EOFException;
    
}
