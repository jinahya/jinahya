package jinahya.bitio;


import java.io.IOException;
import java.io.OutputStream;


/**
 * @author Jin Kwon
 */
public class BitOutputStreamAdapter extends BitOutputImpl {


    /**
     * @param out
     */
    public BitOutputStreamAdapter(final OutputStream out) {
        super(new ByteOutput() {
                public void writeByte(int b) throws IOException {
                    out.write(b);
                }
            });
    }
}