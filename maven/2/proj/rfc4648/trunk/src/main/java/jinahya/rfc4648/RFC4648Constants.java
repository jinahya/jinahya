package jinahya.rfc4648;


/**
 * Constants for rfc4648.
 *
 * @author <a href="mailto:jinahya@gmail.com>Jin Kwon</a>
 */
public final class RFC4648Constants {


    /** Default pad character. '=' */
    public static final char PAD = '=';


    /** Alphabet for 'base64'. */
    public static final String BASE64 =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZabcdef" +
        "ghijklmnopqrstuv" +
        "wxyz0123456789+/";


    /** Alphabet for 'base64url'. */
    public static final String BASE64URL =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZabcdef" +
        "ghijklmnopqrstuv" +
        "wxyz0123456789-_";


    /** Alphabet for 'base32'. */
    public static final String BASE32 =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZ234567";


    /** Alphabet for 'base32hex'. */
    public static final String BASE32HEX =
        "0123456789ABCDEF" +
        "GHIJKLMNOPQRSTUV";


    /** Alphabet for 'base16'. */
    public static final String BASE16 =
        "0123456789ABCDEF";


    private RFC4648Constants() {
        super();
    }
}
