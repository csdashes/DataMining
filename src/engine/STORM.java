package engine;

import engine.utils.ISB;
import engine.utils.Node;
import input_system.InputSystem;
import java.io.EOFException;
import java.util.List;
import org.apache.mahout.common.distance.DistanceMeasure;

/**
 * The calculation engine. Reads the input points, calculates their neighbors,
 * defines and changes their state.
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 * @author Ilias Trichopoulos <itrichop@csd.auth.gr>
 */
public class STORM implements Engine {
    
    private InputSystem is;
    private int k;
    private ISB isb;
    
    /**
     *
     * @param is a class that implements InputSystem interface. It provides the 
     * engine with points.
     * @param dm a class that can calculate distances between points.
     * @param R the radius.
     * @param k the number of neighbors.
     * @param W the window size.
     */
    public STORM(InputSystem is, DistanceMeasure dm, double R, int k, int W){
        this.is = is;
        this.k = k;
        this.isb = new ISB(W, R, dm);
    }
        
    /**
     * Checks the new status of all points after a new point has entered the 
     * window. More precisely, checks if the number of neighbors of all points
     * that were OUTLINERS is now greated or equal to the min number of
     * neighbors, and if yes, changes the status to OUT_TO_INLINER. Also, checks
     * the number of neighbors for the new point and marks it's status.
     * @param ln the list of all points
     * @param new_node the new point
     */
    private void checkStates(List<Node> ln, Node new_node) {
        
        for(Node n : ln) {
            if (n.getState() == Node.State.OUTLINER && n.getCountAfter() + n.getNNBefore().size() >= k) {
                n.setState(Node.State.OUT_TO_INLINER);
            }
        }
                
        if (new_node.getNNBefore().size() >= k) {
            new_node.setState(Node.State.INLINER);
        }
    }
    
    /**
     * Checks the status of a single point.
     * @param n the point
     * @return the point
     */
    private Node soloCheckState(Node n) {
        if (n.getState() == Node.State.OUTLINER && n.getCountAfter() + n.getNNBefore().size() >= k) {
            n.setState(Node.State.OUT_TO_INLINER);
        }
        return n;
    }
    
    /**
     * The main algorithm of <code>STORM</code> calculation engine.
     * Reads the next point from the <code>InputStream</code>, calculates the
     * number of neighbors that their distance from the point is lower than the
     * Parameter R, checks if the state of the point is changed and adds the new 
     * point in the window (if thw window is full, pools the oldest point out).
     * 
     * @return the new point object
     * @throws EOFException If there are no more points in the InputStream,
     * polls all remaining points that are inside the window.
     */
    @Override
    public Node decay() throws EOFException {
        try {
            Node new_node = this.is.nextInterval();
                        
            List<Node> neighbors = this.isb.rangeQuery(new_node);
            
            checkStates(neighbors, new_node);
            
            return this.isb.addNode(new_node);
        } catch (EOFException ex) {
            return soloCheckState(this.isb.poll());
        }
    }
}
