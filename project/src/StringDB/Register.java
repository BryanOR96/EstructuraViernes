/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringDB;

import java.io.IOException;
import java.io.RandomAccessFile;


public class Register {

    private DBFile source;
    int index;
    RandomAccessFile raf;
    DBFormat format;
    String[] fields;

    public Register(DBFile source, int index) {
        this.source = source;
        this.index = index;
        raf = source.raf;
        format = source.format;
        fields = new String[format.getFields().length];
    }

    public int getIndex() {
        return index;
    }

    public String[] getFields() throws IOException {

        raf.seek(index * format.getRegisterLength());
        fields = new String[format.getLengths().length];
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] != null) {
                continue;
            }
            fields[i] = Help.readChars(raf, format.getLengthField(i));

        }

        return fields;
    }

    public String getKey() throws IOException {
        int ind = format.keyField;
        return getField(ind);
    }

    public String getField(int field) throws IOException {
        if (fields[field] != null) {
            return fields[field];
        }
        int pointer = 0;
        for (int i = 0; i < field; i++) {
            pointer += format.getLengthField(i);
        }
        raf.seek(index * format.getRegisterLength() + pointer * DBFormat.CHAR_SIZE);
        return Help.readChars(raf, format.getLengthField(field));
    }

    public String getField(String fieldName) throws IOException {
        return getField(format.indexOf(fieldName));
    }

    public void setField(int field, String data) throws IOException {
        if (data.length() < format.getLengthField(field)) {
            data = data + Help.createString(format.getLengthField(field) - data.length());
        }
        if (data.length() >= format.getLengthField(field)) {
            data = data.substring(0, format.getLengthField(field));
        }
        fields[field] = data;
        int pointer = 0;
        for (int i = 0; i < field; i++) {
            pointer += format.getLengthField(i);
        }
        System.out.println("index "+index);
        raf.seek(index * format.getRegisterLength() + pointer * DBFormat.CHAR_SIZE);
        raf.writeChars(data);
        source.fireDBChangueEvent(2, this);
        if (field == format.keyField) {
            source.fireDBChangueEvent(3, this);
        }

    }

    public void setFields(String[] fields) throws IOException {
        for (int i = 0; i < fields.length; i++) {
            setField(i, fields[i]);

        }
    }
}
