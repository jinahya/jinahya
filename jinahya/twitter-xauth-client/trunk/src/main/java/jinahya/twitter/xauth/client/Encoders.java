

package jinahya.twitter.xauth.client;


import java.io.UnsupportedEncodingException;


/**
 * 
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://tools.ietf.org/html/rfc3986">RFC 3986</a>
 * @see <a href="http://tools.ietf.org/html/rfc4648">RFC 4648</a>
 */
public class Encoders {


    /**
     * 
     * @param input
     * @return
     * @throws UnsupportedEncodingException 
     * @see <a href="http://tools.ietf.org/html/rfc3986#section-2.1">
     *      Percent-Encoding (RFC 3986)</a>
     */
    public static String percent(final String input)
        throws UnsupportedEncodingException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        return percent(input.getBytes("UTF-8"));
    }


    public static String percent(final byte[] input)
        throws UnsupportedEncodingException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        final StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < input.length; i++) {
            final char ch = (char) (input[i] & 0xFF);
            if ((ch >= 0x30 && ch <= 0x39)
                || (ch >= 0x41 && ch <= 0x5A)
                || (ch >= 0x61 && ch <= 0x7A)
                || ch == 0x2D || ch == 0x2E || ch == 0x5F || ch == 0x7E) {
                buffer.append(ch);
            } else {
                buffer.append('%');
                if (ch <= 0x0F) {
                    buffer.append('0');
                }
                buffer.append(Integer.toHexString(ch).toUpperCase());
            }
        }

        return buffer.toString();
    }


    public static String url(final String input)
        throws UnsupportedEncodingException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        return url(input.getBytes("UTF-8"));
    }


    public static String url(final byte[] input) {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < input.length; i++) {
            final char ch = (char) (input[i] & 0xFF);
            if ((ch >= 0x30 && ch <= 0x39)
                || (ch >= 0x41 && ch <= 0x5A)
                || (ch >= 0x61 && ch <= 0x7A)
                || ch == 0x2D || ch == 0x2E || ch == 0x5F || ch == 0x7E) {
                buffer.append(ch);
            } else if (ch == 0x20) {
                buffer.append('+');
            } else {
                buffer.append('%');
                if (ch <= 0x0F) {
                    buffer.append('0');
                }
                buffer.append(Integer.toHexString(ch).toUpperCase());
            }
        }

        return buffer.toString();
    }


    /** The Base 16 Alphabet. */
    private static final char[] BASE16_ALPHABET = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };


    /**
     * Encodes given <code>input</code> into a Base16(hex) string.
     *
     * @param input octet input
     * @return a base 16 encoded string
     * @see <a href="http://tools.ietf.org/html/rfc4648#section-8">Base 16
     *      Encoding (RFC 4648)</a>
     */
    public static String base16(final byte[] input) {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        final char[] output = new char[input.length * 2];

        int octet;
        for (int i = 0; i < input.length; i++) {
            octet = input[i] & 0xFF;
            output[i * 2 + 1] = BASE16_ALPHABET[octet & 0xF];
            output[i * 2] = BASE16_ALPHABET[octet >> 4];
        }

        return new String(output);
    }


    /** The Base 64 Alphabet. */
    private static final char[] BASE64_ALPHABET = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', '+', '/'
    };


    /**
     * Encodes given byte array into a BASE64 string.
     *
     * @param input octet input
     * @return a BASE64 string
     * @see <a href="http://tools.ietf.org/html/rfc4648#section-4">Base 64
     *      Encoding (RFC 4648)</a>
     */
    public static String base64(final byte[] input) {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        int count = input.length / 3;
        if (input.length % 3 > 0) {
            count++;
        }

        final char[] output = new char[count * 4];
        int index = 0;

        int pad = 0;
        int word;
        for (int i = 0; i < input.length; i += 3) {
            word = 0x00;
            for (int j = 0; j < 3; j++) {
                if ((i + j) < input.length) {
                    word <<= 8;
                    word |= (input[i + j] & 0xFF);
                } else {
                    switch (j) {
                        case 1:
                            pad = 2;
                            word <<= 4;
                            break;
                        case 2:
                            pad = 1;
                            word <<= 2;
                            break;
                        default:
                            break;
                    }
                    break;
                }
            }
            for (int j = 0; j < pad; j++) {
                output[index + 3 - j] = '=';
            }
            for (int j = 3 - pad; j >= 0; j--) {
                output[index + j] = BASE64_ALPHABET[word & 0x3F];
                word >>= 6;
            }
            if (pad != 0) {
                break;
            }
            index += 4;
        }

        return new String(output);
    }


    private Encoders() {
        super();
    }
}
