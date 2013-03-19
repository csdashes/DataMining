package datamining;

import java.util.Scanner;

/**
 * This class is used for the communication of the application with the user.
 * Requests from the user all the necessary user-defined parameters, to make the
 * application work.
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 * @author Ilias Trichopoulos <itrichop@csd.auth.gr>
 */
public class Menu {
    
    public Menu() {
    }
    
    /**
     * Requests from the user the window width.
     * @return the window width
     */
    public static int getWindowsWidth() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please give windows width:");
        return scanner.nextInt();
    }
    
    /**
     * Requests from the user the radius size of a clustering neighborhood.
     * It is called Parameter R.
     * @return the radius of a clustering neighborhood
     */
    public static float getParameterR() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please give R:");
        return scanner.nextFloat();
    }
    
    /**
     * Requests from the user the min number of neighbors.
     * It is called Parameter K. It represents the minimum amount of neighbors
     * that a point should have (in a radius of Parameter R) in order to be 
     * accepted as an in-line point.
     * @return the min number of neighbors
     */
    public static int getParameterK() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please give k:");
        return scanner.nextInt();
    }
}
