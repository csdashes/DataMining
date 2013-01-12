/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import engine.utils.ISB;
import engine.utils.Node;
import input_system.InputSystem;
import java.io.EOFException;
import java.util.List;
import org.apache.mahout.common.distance.DistanceMeasure;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class STORM implements Engine {
    
    private InputSystem is;
    private int k;
    private ISB isb;
    
    /**
     *
     * @param is a class that implements InputSystem interface. It provides the engine with points.
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
    
    private Node soloCheckState(Node n) {
        if (n.getState() == Node.State.OUTLINER && n.getCountAfter() + n.getNNBefore().size() >= k) {
            n.setState(Node.State.OUT_TO_INLINER);
        }
        return n;
    }
        
    @Override
    public Node decay() throws EOFException {
        // The main algorithm of STORM calculation engine.
        try {
            // We get the next point.
            Node new_node = this.is.nextInterval();
                        
            // We calculate the number of neighbors that their distance is lower than R of our new point.            
            List<Node> neighbors = this.isb.rangeQuery(new_node);
            
            // We check points state(inliner/outliner) if it is changed.
            checkStates(neighbors, new_node);
            
            // We add the new point to the window. If the window if full, we poll the oldest point.
            return this.isb.addNode(new_node);
        // We need to poll all remaining points that are inside the window.
        } catch (EOFException ex) {
            return soloCheckState(this.isb.poll());
        }
    }
}
