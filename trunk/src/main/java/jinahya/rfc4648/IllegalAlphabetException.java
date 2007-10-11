package jinahya.rfc4648;


/**
 *
 * @author Jin Kwon
 */
public class IllegalAlphabetException extends Exception {

    public IllegalAlphabetException() {
        super();
    }

    public IllegalAlphabetException(char ch) {
        this("Illegal alphabet: " + ch);
    }

    public IllegalAlphabetException(String message) {
        super(message);
    }
}