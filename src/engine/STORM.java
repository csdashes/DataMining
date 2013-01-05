/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import engine.utils.Point;
import input_system.InputSystem;
import java.io.EOFException;
import java.util.LinkedList;
import org.apache.mahout.common.distance.DistanceMeasure;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class STORM implements Engine {
    
    private InputSystem is;
    private DistanceMeasure dm;
    private float R;
    private float k;
    private float W;
    private LinkedList<Point> ll;
    
    /**
     *
     * @param is a class that implements InputSystem interface. It provides the engine with points.
     * @param dm a class that can calculate distances between points.
     * @param R the radius.
     * @param k the number of neighbors.
     * @param W the window size.
     */
    public STORM(InputSystem is, DistanceMeasure dm, float R, float k, float W){
        this.is = is;
        this.dm = dm;
        this.R = R;
        this.k = k;
        this.W = W;
        this.ll = new LinkedList<Point>();
    }
    
    private void calculateNeighbors(Point p) {
        Point tmp_p;
        double d;

        for (int i = 0; i < this.ll.size(); i++) {
            tmp_p = this.ll.get(i);
            d = dm.distance(tmp_p.getDimentions(), p.getDimentions());

            if (d < R) {
                p.increaseNeighbors();
                tmp_p.increaseNeighbors();
            }
        }
    }
    
    private void checkStates(Point p) {
        Point tmp_p;
        
        for (int i = 0; i < this.ll.size(); i++) {
            tmp_p = this.ll.get(i);
            if (tmp_p.getState() == Point.State.OUTLINER && tmp_p.getNeighbors() >= k) {
                tmp_p.setState(Point.State.OUT_TO_INLINER);
            }
        }
        
        if (p.getNeighbors() >= k) {
            p.setState(Point.State.INLINER);
        }
    }
    
    private Point addNewPointToWindow(Point p) {
        this.ll.add(p);
        
        if (this.ll.size() > W) {
           return this.ll.pollFirst();
        } else {
            return null;
        }
    }
    
    @Override
    public Point decay() throws EOFException {
        // The main algorithm of STORM calculation engine.
        try {
            // We get the next point.
            Point tmp_p = this.is.nextInterval();

            // We calculate the number of naighbors that their distance is lower than R of our new point.
            calculateNeighbors(tmp_p);
            
            // We check the points that are inside the window and our new point, if their state(inliner/outliner) is changed.
            checkStates(tmp_p);
            // We add the new point to the window. If the window if full, we poll the oldest point.
            return addNewPointToWindow(tmp_p);
        // We need to poll all remaining points that are inside the window.
        } catch (EOFException ex) {
            if (this.ll.size() > 0) {
                return this.ll.pollFirst();
            } else {
                throw new EOFException();
            }
        }
    }
}
