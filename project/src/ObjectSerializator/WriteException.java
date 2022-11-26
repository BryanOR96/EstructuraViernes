/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectSerializator;


public class WriteException extends RuntimeException{

    public WriteException() {
    }

    public WriteException(String message) {
        super(message);
    }

    public WriteException(String message, Throwable cause) {
        super(message+" for  >>  \n"+cause.getClass().getName()+"  "+cause.getLocalizedMessage(), cause);
    }

    public WriteException(Throwable cause) {
        super("WriteException for  >>  \n"+cause.getClass().getName()+"  "+cause.getLocalizedMessage(),cause);
    }

    public WriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message+" for  >>  \n"+cause.getClass().getName()+"  "+cause.getLocalizedMessage(), cause, enableSuppression, writableStackTrace);
    }
    
}
