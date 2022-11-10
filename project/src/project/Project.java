
package project;


public class Project {

    
    public static void main(String[] args) {
        Cola c = new Cola();
            c.encolar("P", "A","A","2020","A","A","A","A",1,"A","a");
            c.encolar("A", "A","A","2020","A","A","A","A",1,"A","a");
            c.encolar("Y", "A","A","2020","A","A","A","A",1,"A","a");
            c.Mostrar();
           
            
            c.modificar("Y","5050", "1","2","2020","3","4","5",100,"9","562" );
            c.Mostrar();
    
        }
    
}
