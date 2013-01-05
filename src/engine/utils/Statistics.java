/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.utils;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class Statistics {
    private int outliners;
    private int inliners;
    private int out_to_inliners;
    
    public Statistics(){
        this.inliners = 0;
        this.out_to_inliners = 0;
        this.outliners = 0;
    }
    
    public void add(Point p){
        if (p == null) {
            return;
        }
        
        if (p.getState() == Point.State.INLINER){
            this.inliners++;    
        } else if (p.getState() == Point.State.OUTLINER) {
            this.outliners++;
        } else {
            this.out_to_inliners++;
        }
    }
    
    @Override
    public String toString() {
        String point = "outliners: " + this.outliners + " inliners: " + this.inliners + " out_to_inliners: " + this.out_to_inliners;
        return point;
    }
}
