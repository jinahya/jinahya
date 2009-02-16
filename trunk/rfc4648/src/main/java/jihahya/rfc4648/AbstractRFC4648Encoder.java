package jinahya.rfc4648;


import java.io.IOException;


/**
 * @author Jin Kwon
 */
public abstract class AbstractRFC4648Encoder {


    /**
     *
     *
     * @param alphabet
     * @param handler
     */
    public AbstractRFC4648Encoder(String alphabet,
                                  EncodingOutputHandler handler) {
        super();

        this.alphabet = alphabet;
        this.handler = handler;

        pad = RFC4648Constants.pad;
    }


    /**
     *
     *
     * @throws IOException
     */
    public abstract void encodeFinal() throws IOException;


    protected String alphabet;
    protected EncodingOutputHandler handler;
    protected char pad;
}