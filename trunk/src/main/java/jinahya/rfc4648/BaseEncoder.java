package jinahya.rfc4648;


/**
 *
 * @author Jin Kwon
 */
public class BaseEncoder {

    public BaseEncoder(String alphabet) {
        super();

        this.alphabet = alphabet;

        bits = (int)(Math.log(alphabet.length())/Math.log(2.0d));
        bytes = new byte[lcm(8, bits) / 8];
        count = 0;
        chars = new char[bytes.length * 8 / bits];

        finalized = false;
    }


    /**
     */
    public BaseEncoder reset() {
        count = 0;
        finalized = false;
        return this;
    }


    /**
     */
    public char[] encode(byte[] in) {
        return encode(in, 0, in.length);
    }


    /**
     */
    public char[] encode(byte[] in, int off, int len) {
        return _encode(in, off, len, false);
    }


    /**
     */
    public char[] encodeFinal() {
        return encodeFinal(new byte[0]);
    }


    /**
     */
    public char[] encodeFinal(byte[] in) {
        return encodeFinal(in, 0, in.length);
    }


    /**
     */
    public char[] encodeFinal(byte[] in, int off, int len) {
        return _encode(in, off, len, true);
    }


    private char[] _encode(byte[] in, int off, int len, boolean finalize) {

        if (finalized) {
            throw new IllegalStateException("Hasn't been reset");
        }

        if (finalize) {
            finalized = true;
        }

        int dividend = count + len;
        int divisor = bytes.length;
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;

        char[] out = new char[((finalize && remainder > 0) ? (quotient + 1) : quotient) * chars.length];

        int iin = 0;
        int iout = 0;
        for (int i = 0; i < quotient; i++) {
            for (int j = count; j < bytes.length; j++) {
                bytes[j] = in[off + iin++];
            }
            encode();
            for (int k = 0; k < chars.length; k++) {
                out[iout++] = chars[k];
            }
            count = 0;
        }

        for (int j = count; j < bytes.length; j++) {
            bytes[j] = in[off + iin++];
        }
        count = remainder;

        if (finalize && remainder > 0) {
            encode();
            for (int k = 0; k < chars.length; k++) {
                out[iout++] = chars[k];
            }
            count = 0;
        }

        return out;
    }

    private void encode() {
        for (int i = count; i < bytes.length; i++) {
            bytes[i] = 0x00;
        }
        int pad = chars.length - (((bytes.length - count) * 8) / bits);
        int index = 0;
        int available = 8;
        for (int i = 0; i < pad; i++) {
            int discarded = 8 - available;
            int base = (bytes[index] << discarded) >>> discarded;
            if (available > bits) {
                base >>>= (available - bits);
                available -= bits;
            } else {
                int needed = bits - available;
                base <<= needed;
                base |= (bytes[++index] >>> (available = 8 - needed));
            }
            chars[i] = alphabet.charAt(base);
        }
        for (int i = pad; i < chars.length; i++) {
            chars[i] = '=';
        }
    }


    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }


    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    private String alphabet;

    private int bits;
    private byte[] bytes;
    private int count;
    private char[] chars;

    private boolean finalized;
}