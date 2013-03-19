package engine.utils;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.mahout.common.distance.DistanceMeasure;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class ISB {
    
    private ISB_Pivot[] pivot_table;
    private Node[] node_table;
    private int n_pivots, n_nodes;
    private double R;
    private DistanceMeasure dm;
    
    public ISB(int W, double R, DistanceMeasure dm) {
        this.pivot_table = new ISB_Pivot[(int)Math.ceil(Math.log(W))];
        this.node_table = new Node[W];
        this.n_pivots = 0;
        this.n_nodes = 0;
        this.dm = dm;
        this.R = R;
    }
        
    /**
     * Creates and returns a {@link HashMap} with all id, node pairs that are 
     * inside the <code>ISB</code>
     * @return a Map with the pairs {id,node}
     */
    private Map<Integer,Node> getAllNodesHash() {
        
        Map<Integer,Node> tmp_hash = new HashMap<Integer,Node>();
        
        for (Node n: this.node_table) {
            if (n != null) {
                tmp_hash.put(n.getId(), n);
            }
        }
        
        for(ISB_Pivot p : this.pivot_table) {
            if (p != null) {
                tmp_hash.put(p.n.getId(), p.n);
            }
        }
        
        return tmp_hash;
    }
    
    /**
     * Adds a new node and returns the result node.
     * @param n the new point
     * @return the result point
     */
    public Node addNode(Node n) {
        if (this.n_pivots < this.pivot_table.length) {
            ISB_Pivot p = new ISB_Pivot(n);
            this.pivot_table[this.n_pivots] = p;
            this.n_pivots++;
            
            if (this.n_nodes > 0) {
                Node tmp_n;
                for (int i=0; i<this.n_nodes; i++){
                    tmp_n =  this.node_table[i];
                    p.sl.add(new ISB_Node(tmp_n, dm.distance(n.getDimentions(), tmp_n.getDimentions())));
                }
            }
            
            return null;
            
        } else {
            Node result = null;
            
            if (this.n_nodes == this.node_table.length){
                // Get oldest node
                result = this.node_table[0];
                
                // Shift node table
                shiftTable(this.node_table);
                
                this.n_nodes--;
            }
            
            // Get the older pivot and make it a node.
            this.node_table[n_nodes] = this.pivot_table[0].n;
            Node old_pivot = this.pivot_table[0].n;
            
            // Shift the pivot table to delete the oldest pivot.
            shiftTable(this.pivot_table);
            this.n_pivots--;
            
            // Create a new pivot;
            ISB_Pivot new_pivot = new ISB_Pivot(n);
            
            // Calculate distances with the new pivot.
            Node tmp_n;
            for (int i=0; i<this.n_nodes; i++) {
                tmp_n = this.node_table[i];
                new_pivot.sl.add(new ISB_Node(tmp_n, dm.distance(n.getDimentions(), tmp_n.getDimentions())));
            }
            
            // Add the old pivot to the distance list of the other pivots.
            ISB_Pivot p;
            for (int i=0; i<this.n_pivots; i++) {
                p = this.pivot_table[i];
                p.sl.add(new ISB_Node(old_pivot, dm.distance(p.n.getDimentions(), old_pivot.getDimentions())));
            }
            
            // Assign the new pivot at the end of the table.
            this.pivot_table[this.pivot_table.length-1] = new_pivot;
            
            this.n_nodes++;
            this.n_pivots++;
            
            
            return result;
        }
    }
    
    /**
     * Given the node n, scans the ISB and returns all nodes that are closer 
     * than R distance from the point <code>n</code>.
     * @param n the point
     * @return the list of points that are closer than R distance from the input
     * point
     */
    public ArrayList<Node> rangeQuery(Node n) {
        double d;
        Node tmp_p;
        ArrayList<Node> result = new ArrayList<Node>(); 
        
        Map<Integer,Node> node_hash = getAllNodesHash();
        
        // We iterate all pivots and get the distance between n and pivot.
        for (int i = 0; i < this.n_pivots; i++) {
            tmp_p = this.pivot_table[i].n;
            d = dm.distance(n.getDimentions(), tmp_p.getDimentions());
            
            // We get all pre-calculated distances between pivot and all other nodes, so that we can
            // compare it with n.
            for (ISB_Node isbn : this.pivot_table[i].sl) {
                // If we find that the distance is greater than the condition, we dont need to check this node again.
                if (d - isbn.distance > this.R) {
                    node_hash.remove(isbn.n.getId());
                }
            }
            
            // We check the distance of the pivot.
            if (d <= this.R) {
                result.add(tmp_p);
                n.addNNBefore(tmp_p.getId());
                tmp_p.increaseCountAfter();
            }
        }
        
        // We get all candidates and we calculate the distance.
        for (Node it_n : node_hash.values()){
            d = dm.distance(n.getDimentions(), it_n.getDimentions());
            
            if (d <= this.R) {
                result.add(it_n);

                n.addNNBefore(it_n.getId());
                it_n.increaseCountAfter();
            }
        }
        
        return result;
    }

    // This function is returing all remaining nodes of the ISB.
    /**
     * This method is used to return all remaining nodes of the <code>ISB</code>
     * @return a point
     * @throws EOFException 
     */
    public Node poll() throws EOFException {
        ISB_Pivot p;
        for (int i = 0; i < this.n_pivots; i++) {
            p = this.pivot_table[i];
            shiftTable(this.pivot_table);
            this.n_pivots--;
            return p.n;
        }
        
        Node n;
        for (int i = 0; i < this.n_nodes; i++) {
            n = this.node_table[i];
            shiftTable(this.node_table);
            this.n_nodes--;
            return n;
        }
        
        throw new EOFException();
    }

    /**
     * 
     * @param table 
     */
    private void shiftTable(Object[] table) {
        if (table.length == 1) {
            table[0] = null;
            return;
        }
        
        for (int i = 0; i < table.length - 1; i++) {
            table[i] = table[i + 1];
        }
    }
    /**
     * This wrapper class declares a node as a pivot to the <code>ISB</code>. An
     * array keeps references to the nodes and their distance.
     */
    public class ISB_Pivot {
        public Node n;
        public List<ISB_Node> sl;
        
        public ISB_Pivot(Node n) {
            this.n = n;
            this.sl = new ArrayList<ISB_Node>();
        }
    }
    
    /**
     * This wrapper class declares a node as an <code>ISB</code> node that keeps
     * the distance information from a pivot.
     */
    public class ISB_Node implements Comparable<ISB_Node> {
        public Node n;
        public double distance;

        public ISB_Node(Node n, double distance) {
            this.distance = distance;
            this.n = n;
        }
                
        @Override
        public int compareTo(ISB_Node t) {
            return (int)(t.distance - this.distance);
        }
    }
}
