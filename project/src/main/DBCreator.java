
package main;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import StringDB.DBFile;
import StringDB.DBFormat;
import StringDB.Register;


public class DBCreator {

    static String[] keys = {"ID", "Nombre", "fecha de nacimiento", "email", "Categoria"};
    static int[] lengths = {20, 150, 12, 50, 1};
    static String[]names={"Juan ","Pedro ","Celia ","Eva ","Francisco ","Elena ","Maria ","Carlos ","Esteban ","Camilo ","Ana ","Ernesto ","Antonio ","Linda ","Marcos ","Jose "};
    static String[]second={"Ramirez ","Cruz ","Gomez ","Guevara ","Rodriguez ","Nuñez ","Smith ","Morales ","Coba ","Alonso ","Montes ","Perez "};
    static String[]types={"z","g","s","b"};
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            DBFormat format = new DBFormat("clientes", keys, lengths, 0);
            format.save(new File("format.xml"));
           // System.exit(0);
            DBFile file = new DBFile(format, "table.sdb");
            
            format.save();
            Random ran=new Random();
            for (int i = 0; i < 100; i++) {
                String name=names[ran.nextInt(names.length)];
                String first=second[ran.nextInt(second.length)];
                String last=second[ran.nextInt(second.length)];
                Register reg = file.addRegister();
                String id="";
                for (int j = 0; j < 5; j++) {
                     id+=ran.nextInt(9);
                }
                id+=i;
               for (int j = 0; j < 5; j++) {
                     id+=ran.nextInt(9);
                }
               reg.setField(0, id.substring(0,11));
                reg.setField(1,name+first+last);
                reg.setField(2,ran.nextInt(29)+"/"+ran.nextInt(11)+"/"+(ran.nextInt(70)+1960));
                reg.setField(3, name.trim()+"."+first.trim()+ran.nextInt(50)+"@email.com");
                reg.setField(4, types[ran.nextInt(types.length)]);
                
              

            }
        } catch (IOException ex) {
            System.out.println("error >> " + ex.getLocalizedMessage());
        }
        System.out.println("correct creation");
    }

}
