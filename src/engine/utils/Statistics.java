package engine.utils;

/**
 * 
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 * @author Ilias Trichopoulos <itrichop@csd.auth.gr>
 */
public class Statistics {
    private int outliners;
    private int inliners;
    private int out_to_inliners;
    
    /**
     * Initializes the fields.
     */
    public Statistics(){
        this.inliners = 0;
        this.out_to_inliners = 0;
        this.outliners = 0;
    }
    
    /**
     * Updates the statistics according to the status of the pointer.
     * @param p the pointer
     */
    public void add(Node p){
        if (p == null) {
            return;
        }
        
        if (p.getState() == Node.State.INLINER){
            this.inliners++;    
        } else if (p.getState() == Node.State.OUTLINER) {
            this.outliners++;
        } else {
            this.out_to_inliners++;
        }
    }
    
    /**
     * Prints the statistics. The message is in the form "outliners: [outliners] inliners: [inliners]
     * out_to_inliners: [out_to_inliners]".
     * @return the message as a string
     */
    @Override
    public String toString() {
        String point = "outliners: " + this.outliners + " inliners: " + this.inliners + " out_to_inliners: " + this.out_to_inliners;
        return point;
    }
}
