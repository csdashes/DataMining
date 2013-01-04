/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import org.apache.mahout.math.Vector;
import org.apache.mahout.math.RandomAccessSparseVector;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class Point {
    
    private int id;
    private Vector dimentions;
    
    public Point(String line) {
        String[] tokens = line.split(",");
        
        double[] aDimentions = new double[tokens.length-1];
        
        for(int i=1; i<tokens.length; i++) {
            aDimentions[(i-1)] = Double.valueOf(tokens[i]);
        }
        
        this.id = Integer.valueOf(tokens[0]);
        this.dimentions = new RandomAccessSparseVector(tokens.length-1);
        this.dimentions.assign(aDimentions);
    }
    
    public Point(int id, Vector dimentions) {
        this.id = id;
        this.dimentions = dimentions;
    }
    
    public int getId() {
        return this.id;
    }
    
    public Vector getDimentions() {
        return this.dimentions;
    }
}
