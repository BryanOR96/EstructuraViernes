
package ejercicio4;

public class Ejercicio4 {
     
    public static int recurSum(int n)
    {
        if (n <= 1)
            return n;
        return n + recurSum(n - 1);
    }

    
    public static void main(String[] args) {
         int n = 7;
        System.out.println(recurSum(n));
    }
    
}