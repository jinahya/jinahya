package jinahya.rfc4648;


/**
 *
 * @author Jin Kwon
 */
public interface BaseEncoder {

    /**
     */
    public char[] encode(byte[] in);


    /**
     */
    public char[] encode(byte[] in, int off, int len);


    /**
     */
    public char[] encodeFinal();


    /**
     */
    public char[] encodeFinal(byte[] in);


    /**
     */
    public char[] encodeFinal(byte[] in, int off, int len);
}