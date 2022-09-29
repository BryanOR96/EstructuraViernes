
package ejercicio4;

import java.util.Scanner;


public class Ejercicio4 {

    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int num;
        do{
           System.out.print("Introduce un numero entero mayor a 0! ");
            System.out.println("");
           num = teclado.nextInt();
        }while(num<=0);
        System.out.println("Suma desde 1 hasta " + num + " = " + Ejercicio4(num));
    }
    
     
    public static double Ejercicio4(int n){
           if(n == 1)  
              return 1;
            else
              return n + Ejercicio4(n-1);
    }
    
}
