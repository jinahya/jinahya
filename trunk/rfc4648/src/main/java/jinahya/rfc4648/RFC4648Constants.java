package jinahya.rfc4648;


/**
 * Constants for rfc 4648.
 *
 * @author Jin Kwon
 */
public class RFC4648Constants {


    /** Default pad character. '=' */
    public static final char pad = '=';


    /** Alphabet for 'base64' */
    public static final String base64 =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZabcdef" +
        "ghijklmnopqrstuv" +
        "wxyz0123456789+/";


    /** Alphabet for 'base64url' */
    public static final String base64url =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZabcdef" +
        "ghijklmnopqrstuv" +
        "wxyz0123456789-_";


    /** Alphabet for 'base32' */
    public static final String base32 =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZ234567";


    /** Alphabet for 'base32hex' */
    public static final String base32hex =
        "0123456789ABCDEF" +
        "GHIJKLMNOPQRSTUV";


    /** Alphabet for 'base16' */
    public static final String base16 =
        "0123456789ABCDEF";
}