/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectSerializator;


public interface Saveable {
    public void load(DataReader reader)throws ReadException;
    public void print(DataWriter writer)throws WriteException;
}
