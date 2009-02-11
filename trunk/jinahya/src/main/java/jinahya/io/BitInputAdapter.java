package jinahya.io;



/**
 * @author Jin Kwon
 */
public interface BitInputAdapter {

    /**
     * @return
     * @exception IOException if an I/O error occurs
     */
    public int getOctet() throws IOException;
}