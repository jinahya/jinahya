package jinahya.rfc4648;


import java.io.IOException;


/**
 * @author Jin Kwon
 */
public abstract class AbstractRFC4648Decoder {


    /**
     * Creates an instance.
     *
     * @param alphabet
     * @param handler
     */
    public AbstractRFC4648Decoder(String alphabet,
                                  DecodingOutputHandler handler) {
        super();

        this.alphabet = alphabet;
        this.handler = handler;

        pad = RFC4648Constants.pad;
    }


    /**
     *
     *
     * @throws IOException if an I/O error occurs.
     */
    public abstract void decodeFinal() throws IOException;


    protected String alphabet;
    protected DecodingOutputHandler handler;

    protected char pad;
}