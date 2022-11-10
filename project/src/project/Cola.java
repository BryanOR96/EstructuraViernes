
package project;

import javax.swing.JOptionPane;

public class Cola {
    private Persona head;
    private Persona tail;

    public Cola(Persona head, Persona tail) {

        this.head = null;
        this.tail = null;
    }

    Cola() {

    }

    public void encolar(String placa, String marca, String modelo, String año, String color, String cilindraje, String combustible, String caácidad, int alquiler, String info, String eliminar) {

        Persona newNodo = new Persona();
        newNodo.setPlaca(placa);
        newNodo.setMarca(marca);
        newNodo.setModelo(modelo);
        newNodo.setAño(año);
        newNodo.setColor(color);
        newNodo.setCilindraje(cilindraje);
        newNodo.setCombustible(combustible);
        newNodo.setCapacidad(caácidad);
        newNodo.setAlquiler(alquiler);
        newNodo.setInfo(info);
        newNodo.setEliminar(eliminar);
        if (head == null) {

            head = newNodo;
            tail = newNodo;
        } else {

            tail.setNext(newNodo);
            tail = newNodo;

        }

    }

    public void Mostrar() {

        Persona aux = head;
        aux = head;
        if (head != null) {

            while (aux != null) {

                System.out.print("/" + aux.getPlaca() + "-" + aux.getMarca() + "-" + aux.getModelo() + "-" + aux.getAño() + "-" + aux.getColor() + "-" + aux.getCilindraje() + "-" + aux.getCombustible() + "-" + aux.getCapacidad() + "-" + aux.getAlquiler() + "-" + aux.getInfo() + "-" + aux.getEliminar());

                aux = aux.getNext();

            }

        } else {

            System.out.print("\n la cola esta vacia\n ");

        }

    }

    public void eliminar(String placa, String e) {

        Persona aux = new Persona();
        aux.setPlaca(placa);
        aux.setEliminar(e);
        if ("Alquilado".equalsIgnoreCase(aux.getEliminar())) {
            JOptionPane.showMessageDialog(null, "No se puede elimiar esta alquilado");
           

        } else {

            if (head != null) {
                if (head == tail && placa.equalsIgnoreCase(head.getPlaca())) {

                    head = tail = null;

                } else if (aux.getPlaca().equalsIgnoreCase(head.getPlaca())) {

                    head = head.getNext();

                } else {
                    Persona temporal = new Persona();
                    Persona anterior = new Persona();
                    anterior = head;
                    temporal = head.getNext();
                    while (temporal != null && !temporal.getPlaca().equalsIgnoreCase(aux.getPlaca())) {

                        anterior = anterior.getNext();
                        temporal = temporal.getNext();

                    }
                    if (temporal != null) {
                        anterior.setNext(temporal.getNext());

                        if (temporal == tail) {

                            tail = anterior;

                        }

                    }

                }

            }

        }

    }
    
    
    
    public void modificar( String b, String marca, String modelo, String año, String color, String cilindraje, String combustible, String caácidad, int alquiler, String info, String eliminar){
        
        Persona aux = new Persona();
        
        aux = head;
        boolean encontrado = false;
        
        if(head != null){
            
            while(aux != null && encontrado != true){
                
                if(aux.getPlaca().equals(b)){
                    JOptionPane.showMessageDialog(null, "REGISTRO ENCONTRADO");
                    aux.setMarca(marca);
                    aux.setModelo(modelo);
                    aux.setAño(año);
                    aux.setColor(color);
                    aux.setCilindraje(cilindraje);
                    aux.setCombustible(combustible);
                    aux.setCapacidad(caácidad);
                    aux.setAlquiler(alquiler);
                    aux.setInfo(info);
                    aux.setEliminar(eliminar);
                    

                    encontrado = true;
                
                }
                aux = aux.getNext();
            
            
            }
            
        
        }
        
        
    
       
        
        
        
    
    
    }
}
