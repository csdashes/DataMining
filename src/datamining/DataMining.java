/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class DataMining {

    private static float R,k,W;
    private static Point[] Window;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        W = Menu.getWindowsWidth();
        R = Menu.getParameterR();
        k = Menu.getParameterK();
        
        Window = new Point[(int)W];
        
        
    }
}
