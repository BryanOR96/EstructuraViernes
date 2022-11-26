
package main;

import StringDB.DBFile;
import StringDB.Register;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;


public class RentalModel extends DefaultListModel<String> {

    DBFile source;
    static String[] names = {"matricula", "fecha", "dias de alquiler"};
    String sep = "  ,  ";
    List<String[]> values = new ArrayList<>();

    /**
     *
     * @param model
     * @param source
     * @param key
     * @throws IOException
     */
    public RentalModel(DBFile source, String key) throws IOException {

        this.source = source;

        for (int i = 0; i < source.getRegisterCont(); i++) {

            source.seek(i);
            Register reg = source.getRegister();
            if (reg.getField(1).trim().equals(key.trim())) {
                // loadeds.add(reg);
                values.add(reg.getFields());
                addElement(reg.getField(2) + sep + reg.getField(3) + "  ,   " + reg.getField(4));

            }

        }

    }

    public void removeField(int index) throws IOException {
        String key = values.get(index)[0];

        int ind = source.IndexOf(key.trim());
        remove(index);
        values.remove(index);
        source.deleteRegister(ind);
    }

    public void add(String[] datas, String ced) throws IOException {
        String key = "";
        for (String data : datas) {
            key += data;
        }

        Register reg = source.addRegister();
        reg.setField(0, key);
        reg.setField(1, ced);
        reg.setField(2, datas[0]);
        reg.setField(3, datas[1]);
        reg.setField(4, datas[2]);
        addElement(reg.getField(2) + sep + reg.getField(3) + "  ,   " + reg.getField(4));
        values.add(reg.getFields());
    }

}
