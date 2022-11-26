/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectSerializator;

import java.io.*;

import java.util.HashMap;
import java.util.Map;


public class TextDataReader implements DataReader {

    BufferedReader reader;
    Map< Integer, Object> IdTable = new HashMap<>();

    public TextDataReader(Reader reader) {
        this.reader = new BufferedReader(reader);
        readLine();

    }

    public TextDataReader(File source) throws ReadException {
        try {
            this.reader = new BufferedReader(new FileReader(source));
            readLine();
        } catch (FileNotFoundException ex) {
            throw new ReadException("error >> archivo no encontrado : " + source.getPath(), ex);
        }

    }

    @Override
    public void close() throws ReadException {
        try {
            reader.close();
        } catch (Exception e) {
            throw new ReadException("error en cierre de stream", e);
        }
    }

    @Override
    public int readInt() throws ReadException {
        String mark = readItem(INT);

        if (mark != null) {
            int ret = Integer.parseInt(mark);

            return ret;
        } else {
            throw new ReadException("stream invalido>>" + mark);
        }

    }

    @Override
    public double readDouble() throws ReadException {
        String mark = readItem(DOUBLE);

        if (mark != null) {
            //lee datos
            double ret = Double.parseDouble(mark);

            return ret;
        } else {
            throw new ReadException("stream invalido>>" + mark);
        }

    }

    @Override
    public boolean readBoolean() throws ReadException {
        String mark = readItem(BOOLEAN);

        if (mark != null) {
            //lee datos
            boolean ret = Boolean.parseBoolean(mark);

            return ret;
        } else {
            throw new ReadException("stream boolean invalido>>" + mark);//error en marcador de inicio
        }

    }

    @Override
    public String readString() throws ReadException {
        try {
            String mark = read();
            //comprueba marcador de inicio
            if (mark.trim().startsWith(STRING_MARK.trim())) {

                //lee longitud de String
                int size = Integer.parseInt(mark.substring(mark.indexOf(PROP) + 1, mark.lastIndexOf(PROP)));
                //lee caracteres del String
                StringBuilder buff = new StringBuilder();
                //salta espacios en blanco
                for (int i = 0; '"' != (char) reader.read(); i++) ;
                for (int i = 0; i < size; i++) {
                    buff.append((char) reader.read());
                }
                //salta comillas de cierre
                reader.readLine();
                //comprueba marcador de fin
                if (!readLine().trim().endsWith(END_STRING_MARK.trim())) {
                    throw new ReadException("stream invalido ,falta marca de fin");
                }
                return buff.toString();
            } else {
                throw new ReadException("stream invalido");
            }
        } catch (Exception ex) {
            throw new ReadException("IOException  ", ex);
        }
    }

    public String readLine() throws ReadException {
        try {
            String line = reader.readLine();
            Out.print("---------------------readLine   >>>>   " + line);

            return line;
        } catch (IOException ex) {
            throw new ReadException("IOException  ", ex);
        }
    }

    public String read() throws ReadException {
        try {
            String line = reader.readLine();

            if (line.trim().length() == 0) {
                return read();
            }
            int i;
            for (i = 0; Character.isWhitespace(line.charAt(i)) && i < line.length(); i++);

            Out.print("---------------------readLine   >>>>   " + line);

            return line.substring(i);
        } catch (IOException ex) {
            throw new ReadException("IOException  ", ex);
        }
    }

    private String readItem(String target) {
        String line = read();
        int start = line.indexOf(START_MARK);
        int endStart = line.indexOf(END_START_MARK);
        int endFirst = line.indexOf(END_MARK);
        int endLast = line.lastIndexOf(END_MARK);
        String type = line.substring(start + 1, endFirst);
        String value = line.substring(endFirst + 1, endStart);
        if (type.trim().equals(target.trim())) {
            Out.print("readLine   >>>>   " + line + " type = " + type + " , value = " + value);

            return value;

        } else {
            System.out.println("type: " + type);
            System.out.println("value: " + value);
            return null;
        }
    }

    @Override
    public Saveable readObject() throws ReadException {
        try {
            String mark = readLine();
            //comprueba marcador de inicio
            if (mark.contains(OBJECT_MARK)) {
                Saveable sav;
                //lee nombre de clase
                String type = mark.substring(mark.indexOf(PROP) + 1, mark.lastIndexOf(PROP));
                //depuracion
                Out.print("class " + type);

                //lee id del objeto
                int id = Integer.parseInt(readItem(ID));
                //comprueba id del objeto
                if (IdTable.containsKey(id)) {//el objeto ya ha sido cargado
                    sav = (Saveable) IdTable.get(id);
                } else {

                    //crea nueva instancia
                    sav = (Saveable) Class.forName(type).newInstance();
                    //lee objeto
                    sav.load(this);
                    //guarda referencia
                    IdTable.put(id, sav);
                }

                //comprueba marcador de fin
                if (!readLine().contains(END_OBJECT_MARK)) {
                    throw new ReadException("stream invalido-no se encontro marcador " + END_OBJECT_MARK);
                }
                return sav;
            } else {
                throw new ReadException("stream invalido");
            }
        } catch (ClassNotFoundException ex) {
            throw new ReadException("error for ClassNotFoundException  ", ex);
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ReadException("error for InstantiationException  ", ex);
        }
    }

    @Override
    public Saveable readObject(Object target) throws ReadException {

        String mark = readLine();
        //comprueba marcador de inicio
        if (mark.contains(OBJECT_MARK)) {
            Saveable sav;
            //lee nombre de clase
            String type = mark.substring(mark.indexOf(PROP) + 1, mark.lastIndexOf(PROP));
            //depuracion
            Out.print("class " + type);

            //lee id del objeto
            int id = Integer.parseInt(readItem(ID));
            //comprueba id del objeto
            if (IdTable.containsKey(id)) {//el objeto ya ha sido cargado
                sav = (Saveable) IdTable.get(id);
            } else {

                //crea nueva instancia
                sav = (Saveable) target;
                //lee objeto
                sav.load(this);
                //guarda referencia
                IdTable.put(id, sav);
            }

            //comprueba marcador de fin
            if (!readLine().contains(END_OBJECT_MARK)) {
                throw new ReadException("stream invalido-no se encontro marcador " + END_OBJECT_MARK);
            }
            return sav;
        } else {
            throw new ReadException("stream invalido");
        }

    }

    @Override
    public Object readObject(ObjectSaver loader) throws ReadException {
        try {
            String mark = readLine();
            //comprueba marcador de inicio
            if (mark.contains(OBJECT_MARK)) {
                Object sav;
                //lee nombre de clase
                String type = mark.substring(mark.indexOf(PROP) + 1, mark.lastIndexOf(PROP));
                //depuracion
                Out.print("class " + type);

                //lee id del objeto
                int id = Integer.parseInt(readItem(ID));
                //comprueba id del objeto
                if (IdTable.containsKey(id)) {//el objeto ya ha sido cargado
                    sav = IdTable.get(id);
                } else {

                    //crea nueva instancia
                    sav = Class.forName(type).newInstance();
                    //lee objeto usando el ObjectSaver
                    loader.load(sav, this);
                    //guarda referencia
                    IdTable.put(id, sav);
                }

                //comprueba marcador de fin
                if (!readLine().contains(END_OBJECT_MARK)) {
                    throw new ReadException("stream invalido-no se encontro marcador " + END_OBJECT_MARK);
                }
                return sav;
            } else {
                throw new ReadException("stream invalido");
            }
        } catch (ClassNotFoundException ex) {
            throw new ReadException("error for ClassNotFoundException  ", ex);
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ReadException("error for InstantiationException  ", ex);
        }
    }

}
