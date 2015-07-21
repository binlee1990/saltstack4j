package saltstack4j.api.exceptions;

public class ST4JException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ST4JException(String message) {
        super(message);
    }

    public ST4JException(String message, Throwable cause) {
        super(message, cause);
    }

    public ST4JException(Throwable cause) {
        super(cause);
    }
}
