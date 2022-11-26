/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectSerializator;


public interface DataWriter extends SerialData{
    public void writeInt(int value)throws WriteException;
    public void writeDouble(double value)throws WriteException;
    public void writeBoolean(boolean value)throws WriteException;
    public void writeString(String value)throws WriteException;
    public void writeLine(String value)throws WriteException;
    public void writeObject(Saveable value)throws WriteException;
    public void writeObject(Object value,ObjectSaver saver)throws WriteException;
    public void close()throws WriteException;
}
