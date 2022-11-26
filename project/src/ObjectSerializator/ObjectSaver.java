/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectSerializator;


public interface ObjectSaver {
    public void print(Object object,DataWriter writer);
    public Object load(Object object,DataReader loader);
}
