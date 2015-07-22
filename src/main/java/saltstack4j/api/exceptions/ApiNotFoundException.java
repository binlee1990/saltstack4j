package saltstack4j.api.exceptions;

public class ApiNotFoundException extends ST4JException {

    private static final long serialVersionUID = 1L;

    public ApiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiNotFoundException(String message) {
        super(message);
    }

    public ApiNotFoundException(Throwable cause) {
        super(cause);
    }

}
