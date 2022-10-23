
package examen;

import java.util.Scanner;


public class examen {
    
    
    public static void main(String[] args) {
      
        Scanner entrada=new Scanner(System.in);
        
        System.out.println("Introduzca su nombre");
        String nombre=entrada.nextLine();
        
        //System.out.println("Digite su producto a comprar");
        //String producto=entrada.nextLine();
        
        System.out.println("Introduzca su cedula");
        Integer cedula=entrada.nextInt();
        String cedu=String.valueOf(cedula);
        
        
       
        
        stack<String> Nombre = new stack<String>();
        Nombre.push(nombre);
        
        stack<String> Cedula = new stack<String>();
        Cedula.push(cedu);
        
        System.out.println();
        
        
        System.out.println();
      
        
        
        
       /* stack<String> Pila1 = new stack<String>();
        Pila1.push("hola");
        Pila1.push("34543");
        Pila1.push("prueba");
        
        System.out.println(Pila1.pop());
        System.out.println(Pila1.pop());
        System.out.println("");*/

    }
    
}
