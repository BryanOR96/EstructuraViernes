
package estructura;


public class Estructura {

    
    public static void main(String[] args) {
        Arbol arbol=new Arbol();
        arbol.insertar(55);
        arbol.insertar(43);
        arbol.insertar(63);
        arbol.insertar(10);
        System.out.println("Inorden");
        arbol.dispararInorden();
        System.out.println("Postorden");
        arbol.dispararPreorden();
        
    }
    
}
