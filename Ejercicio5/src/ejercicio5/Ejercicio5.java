
package ejercicio5;

public class Ejercicio5 {
    
    
    static void multi(int N, int i)
    {
        
        if (i > 12)
            return ;
     
        
        System.out.println(N + " * " + i + " = " + N * i);
                
        
        multi(N, i + 1);
    }

    
    public static void main(String[] args) {
        
        int N = 5;
     
        
        multi(N, 1);
     }
    
}
