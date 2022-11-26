/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import ObjectSerializator.DataReader;
import ObjectSerializator.DataWriter;
import ObjectSerializator.ReadException;
import ObjectSerializator.Saveable;
import ObjectSerializator.WriteException;
import javax.swing.DefaultListModel;


public class SaveableListModel < E > extends DefaultListModel< E > implements Saveable{

    @Override
    public void load(DataReader reader) throws ReadException {
        int size=reader.readInt();
        for (int i = 0; i < size; i++) {
            addElement((E)reader.readObject());
        }
    }

    @Override
    public void print(DataWriter writer) throws WriteException {
      writer.writeInt(getSize());
        for (int i = 0; i < getSize(); i++) {
            writer.writeObject((Saveable) get(i));
        }
    }
    
}
