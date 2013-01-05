/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import engine.Engine;
import engine.STORM;
import engine.utils.Point;
import input_system.InputSystem;
import input_system.PointGenerator;
import java.io.EOFException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.mahout.common.distance.DistanceMeasure;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;

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
        
//        W = Menu.getWindowsWidth();
//        R = Menu.getParameterR();
//        k = Menu.getParameterK();
        
        W = 2;
        R = 20;
        k = 3;
        
//        PointGenerator pg = new PointGenerator("sample.txt");
//        
//        boolean flag = true;
//        
//        while(flag) {
//            try {
//                System.out.println(pg.nextInterval());
//            } catch (EOFException ex) {
//                flag = false;
//            }
//        }
        
        
//        InputSystem pg2 = new PointGenerator("sample.txt");
//        EuclideanDistanceMeasure edm = new EuclideanDistanceMeasure();
//        
//        double d=-1;
//        try {
//            d = edm.distance(pg2.nextInterval().getDimentions(), pg2.nextInterval().getDimentions());
//        } catch (EOFException ex) {
//            Logger.getLogger(DataMining.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        System.out.println(d);
        
        
        InputSystem pg = new PointGenerator("sample.txt");
        DistanceMeasure edm = new EuclideanDistanceMeasure();
        Engine e = new STORM(pg, edm, R, k, W);
        
        try {
            e.decay();
        } catch (EOFException ex) {
            Logger.getLogger(DataMining.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Window = new Point[(int)W];
        
        
    }
}
