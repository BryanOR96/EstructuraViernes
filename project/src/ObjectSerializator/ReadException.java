/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectSerializator;


public class ReadException extends RuntimeException{

    public ReadException() {
    }

    public ReadException(String message) {
        super(message);
    }

    public ReadException(String message, Throwable cause) {
        super(message+" for  >>  \n"+cause.getClass().getName()+"  "+cause.getLocalizedMessage(), cause);
    }

    public ReadException(Throwable cause) {
        super("ReadException for  >>  \n"+cause.getClass().getName()+"  "+cause.getLocalizedMessage(),cause);
    }

    public ReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message+" for  >>  \n"+cause.getClass().getName()+"  "+cause.getLocalizedMessage(), cause, enableSuppression, writableStackTrace);
    }
    
}
