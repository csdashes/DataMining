/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import engine.Engine;
import engine.STORM;
import engine.utils.Statistics;
import input_system.InputSystem;
import input_system.PointGenerator;
import java.io.EOFException;
import org.apache.mahout.common.distance.DistanceMeasure;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class DataMining {

    private static double R;
    private static int k,W;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        W = Menu.getWindowsWidth();
        R = Menu.getParameterR();
        k = Menu.getParameterK();
        
        
        
        boolean flag = true;
        
        InputSystem pg = new PointGenerator("sample.txt");
        DistanceMeasure edm = new EuclideanDistanceMeasure();
        Engine e = new STORM(pg, edm, R, k, W);
        Statistics s = new Statistics();
        
        while(flag) {
            try {
                s.add(e.decay());
            } catch (EOFException ex) {
                flag = false;
            }
        }
        
        System.out.println(s);
    }
}
