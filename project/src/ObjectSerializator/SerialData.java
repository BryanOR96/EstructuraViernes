/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectSerializator;


public interface SerialData {
    public static final String STRING="String";
    public static final String INT="int";
    public static final String DOUBLE="double";
    public static final String BOOLEAN="boolean";
    public static final String ID="id";
    public static final String START_DOC_MARK="<java>";
    public static final String END_DOC_MARK="</java>";
    public static final String STRING_MARK="<String length = \"";
    public static final String OBJECT_MARK="<Object class = \"";
    public static final String END_STRING_MARK="</String> ";
    public static final String END_OBJECT_MARK="</Object> ";
    public static final String START_MARK="<";
    public static final String END_START_MARK="</";
    public static final String END_MARK=">";
    public static final String PROP="\"";
}
