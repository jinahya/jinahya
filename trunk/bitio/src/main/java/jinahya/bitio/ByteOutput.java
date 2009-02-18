package jinahya.bitio;


import java.io.IOException;


/**
 * @author Jin Kwon
 */
public interface ByteOutput {

    /**
     * @param b
     * @exception IOException if an I/O error occurs
     */
    public void writeByte(int b) throws IOException;
}