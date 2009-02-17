package jinahya.rfc4648;


/**
 *
 *
 * @author Jin Kwon
 */
public class RFC4648Constants {


    /**
     */
    public static final char pad = '=';


    /**
     */
    public static final String base64 =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZabcdef" +
        "ghijklmnopqrstuv" +
        "wxyz0123456789+/";


    /**
     */
    public static final String base64url =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZabcdef" +
        "ghijklmnopqrstuv" +
        "wxyz0123456789-_";


    /**
     */
    public static final String base32 =
        "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZ234567";


    /**
     */
    public static final String base32hex =
        "0123456789ABCDEF" +
        "GHIJKLMNOPQRSTUV";


    /**
     */
    public static final String base16 =
        "0123456789ABCDEF";
}