package jinahya.io;



/**
 * @author Jin Kwon
 */
public interface BitOutputAdapter {

    /**
     * @param octet
     * @exception IOException if an I/O error occurs
     */
    public void putOctet(int octet) throws IOException;
}