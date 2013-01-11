/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import engine.utils.Node;
import java.io.EOFException;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public interface Engine {
    
    public Node decay() throws EOFException;
    
}
