package jinahya.rfc4648;

/**
 *
 * @author Jin Kwon
 */
public class BadPaddingException extends Exception {

    public BadPaddingException() {
        super("Bad padding");
    }

    public BadPaddingException(String message) {
        super(message);
    }
}
