package jinahya.rfc4648;


import java.io.IOException;


public interface DecodingOutputHandler {

    /**
     *
     *
     * @param decoded
     * @throws IOException if an I/O error occurs
     */
    public void decoded(int decoded) throws IOException;
}