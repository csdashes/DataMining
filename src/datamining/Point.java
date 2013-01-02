/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import org.apache.mahout.math.Vector;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class Point {
    
    private int id;
    private Vector dimentions;
    
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
