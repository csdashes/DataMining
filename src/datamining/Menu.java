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
    
    static int getWindowsWidth() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please give windows width:");
        return scanner.nextInt();
    }
    
    static float getParameterR() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please give R:");
        return scanner.nextFloat();
    }
    
    static int getParameterK() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please give k:");
        return scanner.nextInt();
    }
}
