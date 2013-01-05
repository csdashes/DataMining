/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package input_system;

import engine.utils.Point;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class PointGenerator implements InputSystem {
    
    private Scanner scanner;
    
    public PointGenerator() {
        this.scanner = null;
    }
    
    public PointGenerator(String fileName) {
        try {        
            this.scanner = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PointGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Point nextInterval() throws EOFException {
        // For each line of this.file, get line and split to: id, dimentions...
        if (scanner.hasNextLine()) {
             return new Point(scanner.nextLine());
        } else {
            throw new EOFException();
        }
    }
    
    @Override
    protected void finalize ()  {
        scanner.close();
    }
    
}
