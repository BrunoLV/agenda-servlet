package br.com.valhala.agenda.erro;

public class WebAppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WebAppException(String message, Throwable cause) {
        super(message, cause);
    }

}
