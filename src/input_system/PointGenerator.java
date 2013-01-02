/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package input_system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.mahout.math.Vector;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class PointGenerator {
    
    private FileInputStream file;
    
    public PointGenerator() {
        this.file = null;
    }
    
    public PointGenerator(String filePath) {
        try {
            this.file = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException ex) {
            this.file = null;
            Logger.getLogger(PointGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector nextInterval() {
        // For each line of this.file, get line and split to: id, dimentions...
        return null;
    }
}
