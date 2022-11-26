/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringDB;

/**
 *
 * @author YULIER
 */
public class KeyField implements Comparable<KeyField>{
    protected DBFile source;
    protected String key;
    protected int index;

    public KeyField(DBFile source, int index,String key) {
        this.source = source;
        this.index = index;
        this.key=key;
    }
    public Register getRegister(){
        source.seek(index);
        return source.getRegister();
    }
    public DBFile getSource() {
        return source;
    }

    public String getKey() {
        return key;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(KeyField o) {
       return key.compareToIgnoreCase(o.key);
    }

    @Override
    public String toString() {
        return  key ;
    }
    
   
    
}
