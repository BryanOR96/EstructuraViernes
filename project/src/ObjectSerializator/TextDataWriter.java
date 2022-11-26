/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectSerializator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TextDataWriter implements DataWriter {

    Map<Object, Integer> IdTable = new HashMap<>();
    List<Object> objectSet = new ArrayList<>();
    BufferedWriter writer;
    static boolean debug;
    int ident = 0;
    private int nextid;

    public TextDataWriter(Writer writer) {
        this.writer = new BufferedWriter(writer);
        writeLine(START_DOC_MARK);
        ident++;

    }

    public TextDataWriter(File dest) {
        try {
            this.writer = new BufferedWriter(new FileWriter(dest));
            writeLine(START_DOC_MARK);
            ident++;
        } catch (IOException ex) {
            throw new ReadException("TextDatWriter error ; file : " + dest.getPath(), ex);
        }

    }

    public void close() {
        ident = 0;
        newLine();
        try {
            writer.write(END_DOC_MARK);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
        }
    }

    @Override
    public void writeInt(int value) throws WriteException {
        try {
            addIdent();
            writeItem(INT, String.valueOf(value));
            writer.flush();

        } catch (Exception ex) {
            throw new WriteException("writeInt error", ex);
        }
    }

    @Override
    public void writeDouble(double value) throws WriteException {
        addIdent();
        writeItem(DOUBLE, String.valueOf(value));

    }

    @Override
    public void writeBoolean(boolean value) throws WriteException {
        addIdent();
        writeItem(BOOLEAN, String.valueOf(value));

    }

    @Override
    public void writeString(String value) throws WriteException {
        try {
            addIdent();
            write(STRING_MARK + String.valueOf(value.length()) + PROP + END_MARK);
            ident++;
            newLine();
            addIdent();

            writer.write('"');
            for (int i = 0; i < value.length(); i++) {
                writer.write(value.charAt(i));

            }
            writer.write('"');
            ident--;
            newLine();
            addIdent();
            write(END_STRING_MARK);
            newLine();
            writer.flush();
        } catch (Exception ex) {
            throw new WriteException("writeInt error", ex);
        }
    }

    @Override
    public void writeLine(String value) throws WriteException {
        try {
            write(value);
            newLine();
            writer.flush();
        } catch (Exception ex) {
            throw new WriteException("writeLine error", ex);
        }
    }

    private void writeItem(String type, String value) throws WriteException {
        String toPrint = START_MARK + type + END_MARK + value + END_START_MARK + type + END_MARK;
        try {
            write(toPrint);
            newLine();
        } catch (IOException ex) {
            throw new WriteException(ex);
        }
    }

    @Override
    public void writeObject(Saveable value) throws WriteException {

        try {
            addIdent();
            //escribe marcador de inicio y nombre de clase
            write(OBJECT_MARK + value.getClass().getName() + PROP + END_MARK);

            ident++;
            newLine();

            if (objectSet.contains(value) && objectSet.get(objectSet.indexOf(value)) == value) {//el objeto ya ha sido guardado
                addIdent();
                //solo escribe id del objeto
                writeItem(ID, String.valueOf(IdTable.get(value)));
            } else {
                addIdent();
                //siguiente id valido
                ident++;
                //guarda id
                writeItem(ID, String.valueOf(nextId()));
                //añade objeto e id a la tabla
                objectSet.add(value);
                IdTable.put(value, nextid);
                //guarda estado de objeto
                value.print(this);
                ident--;

            }
            ident--;
            addIdent();
            //escribe marcador de fin
            writer.write(END_OBJECT_MARK);
            newLine();
        } catch (Exception ex) {
            throw new WriteException("writeInt error", ex);
        }
    }

    @Override
    public void writeObject(Object value, ObjectSaver saver) throws WriteException {

        try {
            addIdent();
            //escribe marcador de inicio y nombre de clase
            write(OBJECT_MARK + value.getClass().getName() + PROP + END_MARK);

            ident++;
            newLine();

            if (objectSet.contains(value) && objectSet.get(objectSet.indexOf(value)) == value) {//el objeto ya ha sido guardado
                addIdent();
                //solo escribe id del objeto
                writeItem(ID, String.valueOf(IdTable.get(value)));
            } else {
                addIdent();
                //siguiente id valido
                ident++;
                //guarda id
                writeItem(ID, String.valueOf(nextId()));
                //guarda estado de objeto husando el ObjectSaver
                //añade objeto e id a la tabla
                objectSet.add(value);
                IdTable.put(value, nextid);
                saver.print(value, this);
                ident--;

            }
            ident--;
            addIdent();
            //escribe marcador de fin
            writer.write(END_OBJECT_MARK);
            newLine();
        } catch (Exception ex) {
            throw new WriteException("writeInt error", ex);
        }
    }

    private void addIdent() {
        for (int i = 0; i < ident; i++) {
            try {
                writer.write('\t');
            } catch (IOException ex) {
            }
        }
    }

    private void newLine() {
        try {
            writer.newLine();

            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(TextDataWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void write(String text) throws IOException {

        if (debug) {
            System.out.println("ObjectSerializator.TextDataWriter.write() >>  " + text);
        }
        writer.write(String.valueOf(text));

        writer.flush();
        //
    }

    private int nextId() {
        return ++nextid;
    }

}
