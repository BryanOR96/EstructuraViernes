/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringDB;

import ObjectSerializator.DataReader;
import ObjectSerializator.DataWriter;
import ObjectSerializator.ReadException;
import ObjectSerializator.Saveable;
import ObjectSerializator.TextDataReader;
import ObjectSerializator.TextDataWriter;
import ObjectSerializator.WriteException;
import java.io.File;

/**
 * define el formato de un DBFile
 *
 */
public class DBFormat implements Saveable {

    public static final int CHAR_SIZE = Character.SIZE / Byte.SIZE;
    String name;
    int[] lengths;
    String[] fields;
    int keyField;
    File source;
   

    /**
     * solo para serializacion , no husar
     */
    public DBFormat() {

    }

    /**
     * crea un dbFormat con los parametros especificados
     *
     * @param name el nombre del formato
     * @param keys los nombres de los campos de los registro
     * @param lengths las longitudes(en caracteres) de los campos de los
     * registro
     * @param keyField el indice del campo clave
     */
    public DBFormat(String name, String[] keys, int[] lengths, int keyField) {
        this.name = name;
        this.lengths = lengths;
        this.fields = keys;
        this.keyField = keyField;
        
    }

    /**
     * lee un a definicion de formato de un archivo
     *
     * @param source
     */
    public DBFormat(File source) {
        this();
        this.source = source;
        DataReader reader = new TextDataReader(source);
        reader.readObject(this);
       
    }

    public void save() {
        TextDataWriter writer = new TextDataWriter(source);
        writer.writeObject(this);
        writer.close();
    }

    /**
     * guarda la definicion de formato en un archivo
     *
     * @param f
     */
    public void save(File f) {
        source = f;
        TextDataWriter writer = new TextDataWriter(source);
        writer.writeObject(this);
        writer.close();
    }

    public String getName() {
        return name;
    }

    public int[] getLengths() {
        return lengths;
    }

    public String[] getFields() {
        return fields;
    }

    public int getLengthField(int index) {
        return lengths[index];
    }

    public int indexOf(String key) {
        for (int i = 0; i < lengths.length; i++) {
            String k = fields[i];
            if (k.equals(key)) {
                return i;
            }

        }
        return -1;
    }

    public int sizeOf(String key) {
        return getLengthField(indexOf(key));
    }

    public int getRegisterLength() {
        int total = 0;
        for (int length : lengths) {
            total += length;
        }
        return total * CHAR_SIZE;
    }

    @Override
    public void load(DataReader reader) throws ReadException {
        name = reader.readString();
        int length = reader.readInt();
        fields = new String[length];
        lengths = new int[length];
        for (int i = 0; i < lengths.length; i++) {
            fields[i] = reader.readString();
            lengths[i] = reader.readInt();

        }
        keyField = reader.readInt();
    }

    @Override
    public void print(DataWriter writer) throws WriteException {
        writer.writeString(name);
        writer.writeInt(lengths.length);

        for (int i = 0; i < lengths.length; i++) {
            writer.writeString(fields[i]);
            writer.writeInt(lengths[i]);

        }

        writer.writeInt(keyField);
    }

}
