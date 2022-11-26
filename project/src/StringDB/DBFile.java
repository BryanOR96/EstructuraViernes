/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringDB;

import ObjectSerializator.ReadException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clase que representa una tabla de acceso aleatorio almacenada en un archivo
 * mantiene los registros en un orden arbitrario se implementa mediante
 * registros de longitud fija
 *
 */
public class DBFile extends File {

    public static final int NAME_SIZE = 50;
    DBFormat format;
    RandomAccessFile raf;
    int pointer;
    KeyField[] fields;
    List<DBChangueListener> listeners = new ArrayList<>();

    /**
     * abre un archivo con un DBFile para lectura/escritura si el archivo no
     * existe se crea uno vacio
     *
     * @param format el formato del archivo
     * @param file el archivo a leer
     * @throws IOException
     */
    public DBFile(DBFormat format, File file) throws IOException {
        this(format, file.getPath());

    }

    /**
     * abre un archivo con un DBFile para lectura/escritura si el archivo no
     * existe se crea uno vacio
     *
     * @param format el formato del archivo
     * @param pathname la ruta del archivo
     * @throws IOException
     */
    public DBFile(DBFormat format, String pathname) throws IOException {
        super(pathname);
        this.format = format;
        raf = new RandomAccessFile(pathname, "rw");
        fields = new KeyField[getRegisterCont()];

    }

    /**
     * agrega un oyente de eventos DBChaDBChangueListener a la lista de oyentes
     *
     * @param listener el oyente a agregar
     */
    public void addDBChangueListener(DBChangueListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    /**
     * remueve un oyente de eventos DBChaDBChangueListener a la lista de oyentes
     *
     * @param listener el oyente a agregar
     */
    public void removeDBChangueListener(DBChangueListener listener) {
        listeners.remove(listener);
    }

    protected void fireDBChangueEvent(int eventType, Register target) {
        for (DBChangueListener listen : listeners) {
            listen.DBChangue(eventType, target);
        }
    }

    /**
     * retorna las clave de la tabla
     *
     * @return las claves
     * @throws IOException en caso de error de lectura
     */
    public String[] getKeys() throws IOException {
        String[] keys = new String[getRegisterCont()];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = new Register(this, i).getField(format.keyField);

        }
        return keys;
    }

    /**
     * retorna los campos claves de la tabla
     *
     * @return los campos claves de la tabla
     * @throws IOException @throws IOException en caso de error de lectura
     */
    public KeyField[] getKeyFields() throws IOException {
        String[] keys = getKeys();
        fields = new KeyField[keys.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new KeyField(this, i, keys[i]);
        }
        return fields;
    }

    /**
     * mueve el apuntador de registro al indice especificado, las operaciones de
     * lectura y escritura se realizaran en este
     *
     * @param registerIndex el indice del registro
     */
    public void seek(int registerIndex) {
        if (registerIndex > getRegisterCont() - 1) {
            throw new IndexOutOfBoundsException("registro invalido");
        }
        pointer = registerIndex;
    }

    /**
     * retorna el numero de registros de la tabla
     *
     * @return
     */
    public int getRegisterCont() {
        return (int) (length() / format.getRegisterLength());
    }

    /**
     * lee el registro apuntado por la ultima operacion seek()
     *
     * @return el registro leido
     */
    public Register getRegister() {
        return new Register(this, pointer);
    }

    /**
     * agrega un nuevo registro en blanco al final de la tabla, mueve el
     * apuntador de la tabla a el nuevo registro
     *
     * @return el registro agregado
     * @throws IOException en caso de error en la escritura
     */
    public Register addRegister() throws IOException {
        long pos = length();
        raf.setLength(length() + format.getRegisterLength());
        Register newReg = new Register(this, (int) (pos / format.getRegisterLength()));
        for (int i = 0; i < format.getFields().length; i++) {
            newReg.setField(i, Help.createString(format.getLengthField(i)));
        }

        fireDBChangueEvent(0, newReg);
        seek((int) (pos / format.getRegisterLength()));

        return newReg;
    }
    public int IndexOf(String key) throws IOException{
        key=key.trim();
        for (int i = 0; i < getRegisterCont(); i++) {
            if(key.equals( new Register(this, i).getField(format.keyField).trim())){
                return i;
            }

        }
        
        return -1;
       
    }

    /**
     * elimina un registro de la tabla, causando un reordenamiento de la misma
     *
     * @param index el ndice del registro a eliminar
     * @throws IOException en caso de error de escritura
     */
    public void deleteRegister(int index) throws IOException {
        System.out.println("delete");
        if (index == getRegisterCont() - 1) {
            raf.setLength(index * format.getRegisterLength());
        }
        Register replace = new Register(this, getRegisterCont() - 1);
        System.out.println("replace index = " + replace.getIndex());
        String[] fields=replace.getFields();
        Register toDelete = new Register(this, index);
        fireDBChangueEvent(1, toDelete);
        toDelete.setFields(fields);
        raf.setLength((getRegisterCont() - 1) * format.getRegisterLength());

        fireDBChangueEvent(2, toDelete);

    }

    /**
     * interfaz oyente de eventos de modidficacion de tabla
     */
    public static interface DBChangueListener {

        public static final int ADD_CHANGUE = 0;
        public static final int DELETE_CHANGUE = 1;
        public static final int UPDATE_CHANGUE = 2;
        public static final int KEY_CHANGUE = 3;

        public abstract void DBChangue(int ChangueType, Register target);
    }
}
