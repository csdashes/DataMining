package engine.utils;

import java.util.TreeSet;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.Vector.Element;

/**
 * This class represents a pointer.
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 * @author Ilias Trichopoulos <itrichop@csd.auth.gr>
 */
public class Node {
        
    private int id;
    private Vector dimentions;
    private int count_after;
    private TreeSet<Integer> nn_before;
    private State state;
    
    /**
     * Initializes the pointer, reading the coordinates from the input line.
     * @param line the line from the input file
     */
    public Node(String line) {
        String[] tokens = line.split(",");
        
        double[] aDimentions = new double[tokens.length-1];
        
        for(int i=1; i<tokens.length; i++) {
            aDimentions[(i-1)] = Double.valueOf(tokens[i]);
        }
        
        this.id = Integer.valueOf(tokens[0]);
        this.dimentions = new RandomAccessSparseVector(tokens.length-1);
        this.dimentions.assign(aDimentions);
        
        this.count_after = 1;
        
        this.state = State.OUTLINER;
        
        this.nn_before = new TreeSet<Integer>();
    }
    
    public Node(int id, Vector dimentions) {
        this.id = id;
        this.dimentions = dimentions;
        this.count_after = 1;
        this.state = State.OUTLINER;
        this.nn_before = new TreeSet<Integer>();
    }
    
    public int getId() {
        return this.id;
    }
    
    public Vector getDimentions() {
        return this.dimentions;
    }
    
    public void increaseCountAfter() {
        this.count_after++;
    }
    
    public int getCountAfter() {
        return this.count_after;
    }
    
    public State getState() {
        return this.state;
    }
    
    public void setState(State s) {
        this.state = s;
    }
    
    public TreeSet<Integer> getNNBefore() {
        return this.nn_before;
    }
    
    public boolean addNNBefore(int id) {
        return this.nn_before.add(id);
    }
    
    public boolean removeNNBefore(int id) {
        return this.nn_before.remove(id);
    }
    
    public boolean containsNNBefore(int id) {
        return this.nn_before.contains(id);
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
