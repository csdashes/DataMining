/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import input_system.PointGenerator;
import java.io.EOFException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        PointGenerator pg = new PointGenerator("sample.txt");
        
        boolean flag = true;
        
        while(flag) {
            try {
                System.out.println(pg.nextInterval());
            } catch (EOFException ex) {
                flag = false;
            }
        }
        
        
        W = Menu.getWindowsWidth();
        R = Menu.getParameterR();
        k = Menu.getParameterK();
        
        Window = new Point[(int)W];
        
        
    }
}
