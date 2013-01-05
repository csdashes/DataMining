/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package input_system;

import engine.utils.Point;
import java.io.EOFException;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public interface InputSystem {
    
    public Point nextInterval() throws EOFException;
    
}
