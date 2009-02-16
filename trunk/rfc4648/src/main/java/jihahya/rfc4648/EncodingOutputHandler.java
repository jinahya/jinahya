package jinahya.rfc4648;


import java.io.IOException;


/**
 * @author Jin Kwon
 */
public interface EncodingOutputHandler {

    /**
     *
     * @param encoded
     * @throws IOException if an I/O error occurs
     */
    public void encoded(char encoded) throws IOException;
}