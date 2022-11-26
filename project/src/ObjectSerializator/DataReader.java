/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectSerializator;


public interface DataReader extends SerialData{
    public int readInt()throws ReadException;
    public double readDouble()throws ReadException;
    public boolean readBoolean()throws ReadException;
    public String readString()throws ReadException;
    public String readLine()throws ReadException;
    public Saveable readObject()throws ReadException;
    public Saveable readObject(Object target)throws ReadException;
    public Object readObject(ObjectSaver loader)throws ReadException;
    public void close()throws ReadException;
    
}
