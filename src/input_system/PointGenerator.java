package input_system;

import engine.utils.Node;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple file reader class that reads from a file and creates points.
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 * @author Ilias Trichopoulos <itrichop@csd.auth.gr>
 */
public class PointGenerator implements InputSystem {
    
    private Scanner scanner;
    
    public PointGenerator() {
        this.scanner = null;
    }
    
    /**
     * Creates a new {@link Scanner} for the <code>fileName</code> given.
     * @param fileName the name of the input file that contains the points with
     * their coordinates
     */
    public PointGenerator(String fileName) {
        try {        
            this.scanner = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PointGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Read the lines of the input file and return a new <code>Node</code>
     * object.
     * @return a new pointer
     * @throws EOFException 
     */
    @Override
    public Node nextInterval() throws EOFException {
        // For each line of this.file, get line and split to: id, dimentions...
        if (scanner.hasNextLine()) {
             return new Node(scanner.nextLine());
        } else {
            throw new EOFException();
        }
    }
    
    /**
     * Closes the <code>Scanner</code>
     */
    @Override
    protected void finalize ()  {
        scanner.close();
    }
    
}
