/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import java.util.Scanner;

/**
 *
 * @author Anastasis Andronidis <anastasis90@yahoo.gr>
 */
public class Menu {
    
    static float getWindowsWidth() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please give windows width:");
        return scanner.nextFloat();
    }
    
    static float getParameterR() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please give R:");
        return scanner.nextFloat();
    }
    
    static float getParameterK() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please give k:");
        return scanner.nextFloat();
    }
}
