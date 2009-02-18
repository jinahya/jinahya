package jinahya.bitio;


import java.io.IOException;
import java.io.InputStream;


/**
 * @author Jin Kwon
 */
public class BitInputStreamAdapter extends BitInputImpl {


    /**
     * @param in
     */
    public BitInputStreamAdapter(final InputStream in) {
        super(new ByteInput() {
                public int readByte() throws IOException {
                    return in.read();
                }
            });
    }
}