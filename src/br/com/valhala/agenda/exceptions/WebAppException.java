/**
 *
 */
package br.com.valhala.agenda.exceptions;

/**
 * @author bruno
 */
public class WebAppException extends Exception {

    private static final long serialVersionUID = 1L;

    public WebAppException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public WebAppException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public WebAppException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public WebAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param cause
     */
    public WebAppException(Throwable cause) {
        super(cause);
    }

}
