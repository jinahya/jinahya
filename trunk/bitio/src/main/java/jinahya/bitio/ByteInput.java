package jinahya.bitio;


import java.io.IOException;


public interface ByteInput {

    /**
     * @return
     * @exception IOException if an I/O error occurs
     */
    public int readByte() throws IOException;
}