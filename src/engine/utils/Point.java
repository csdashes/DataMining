/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.utils;

import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.Vector.Element;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class Point {
    
    private int id;
    private Vector dimentions;
    private int neighbors;
    private State state;
    
    public Point(String line) {
        String[] tokens = line.split(",");
        
        double[] aDimentions = new double[tokens.length-1];
        
        for(int i=1; i<tokens.length; i++) {
            aDimentions[(i-1)] = Double.valueOf(tokens[i]);
        }
        
        this.id = Integer.valueOf(tokens[0]);
        this.dimentions = new RandomAccessSparseVector(tokens.length-1);
        this.dimentions.assign(aDimentions);
        this.neighbors = 0;
        this.state = State.OUTLINER;
    }
    
    public Point(int id, Vector dimentions) {
        this.id = id;
        this.dimentions = dimentions;
        this.neighbors = 0;
        this.state = State.OUTLINER;
    }
    
    public int getId() {
        return this.id;
    }
    
    public Vector getDimentions() {
        return this.dimentions;
    }
    
    public void increaseNeighbors() {
        this.neighbors++;
    }
    
    public int getNeighbors() {
        return this.neighbors;
    }
    
    public State getState() {
        return this.state;
    }
    
    public void setState(State s) {
        this.state = s;
    }
    
    @Override
    public String toString() {
        String point = "id: " + this.id + " Dimentions: ";
        
        for(Element f : this.dimentions){
            point = point + f.get() + " ";
        }
        
        point += "State: " + this.state;
        
        return point;
    }
    
    public enum State {
        OUTLINER, INLINER, OUT_TO_INLINER
    }
}
