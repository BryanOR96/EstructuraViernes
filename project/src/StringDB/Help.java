/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringDB;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author YULIER
 */
public class Help {
    public static String readChars(RandomAccessFile raf,int length) throws IOException{
        StringBuilder builder=new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(raf.readChar());
        }
        return builder.toString();
    }
    public static String createString(int length){
         StringBuilder builder=new StringBuilder(length);
         for (int i = 0; i <length ; i++) {
            builder.append(' ');
        }
        return builder.toString();
    }
}
