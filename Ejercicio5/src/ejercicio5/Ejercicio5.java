
package ejercicio5;
 import javax.swing.JOptionPane;

public class Ejercicio5 {

    
    public static void main(String[] args) {
        int d;
        String temp;
        temp=JOptionPane.showInputDialog("Que tabla de multiplicar vas a utilizar");
        d=Integer.parseInt(temp);
        JOptionPane.showMessageDialog(null,tabla(12,d));       
    }
    
    public static String tabla(int a, int d){
        if (a==1){
            return d+" x "+1+" = "+d+"\n";
        }else{
            int b;
            b=a;
            return tabla(a-1,d)+d+" x "+b+" = "+(d*b)+"\n";
        }
    
    }
    
}
