package jinahya.rfc4648;


/**
 *
 * @author Jin Kwon
 */
public interface BaseDecoder {

    /**
     */
    public BaseDecoder reset();


    public byte[] decode(char[] in) throws IllegalAlphabetException;


    public byte[] decode(char[] in, int off, int len)
        throws IllegalAlphabetException;


    public byte[] decodeFinal()
        throws IllegalAlphabetException, BadPaddingException;


    public byte[] decodeFinal(char[] in)
        throws IllegalAlphabetException, BadPaddingException;


    public byte[] decodeFinal(char[] in, int off, int len)
        throws IllegalAlphabetException, BadPaddingException;
}