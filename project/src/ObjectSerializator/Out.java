/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectSerializator;



public class Out {
    public static boolean active=false;
    public static void print(Object s){
        if(active){
            System.out.println(s);
        }
    }
}
