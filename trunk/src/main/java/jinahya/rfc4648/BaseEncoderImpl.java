package jinahya.rfc4648;


/**
 *
 * @author Jin Kwon
 */
class BaseEncoderImpl implements BaseEncoder {

    public BaseEncoderImpl(char[] alphabet) {
        super();

        this.alphabet = alphabet;

        bits = (int)(Math.log(alphabet.length)/Math.log(2.0d));
        bytes = new byte[lcm(8, bits) / 8];
        count = 0;
        chars = new char[bytes.length * 8 / bits];
        finalized = false;
    }


    /** {@inheritDoc} */
    public BaseEncoderImpl reset() {
        count = 0;
        finalized = false;
        return this;
    }


    /** {@inheritDoc} */
    public char[] encode(byte[] in) {
        return encode(in, 0, in.length);
    }


    /** {@inheritDoc} */
    public char[] encode(byte[] in, int off, int len) {
        return _encode(in, off, len, false);
    }


    /** {@inheritDoc} */
    public char[] encodeFinal() {
        return encodeFinal(new byte[0]);
    }


    /** {@inheritDoc} */
    public char[] encodeFinal(byte[] in) {
        return encodeFinal(in, 0, in.length);
    }


    /** {@inheritDoc} */
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
            for (int j = count; j < bytes.length; j++, count++) {
                bytes[j] = in[off + iin++];
            }
            encode();
            for (int j = 0; j < chars.length; j++) {
                out[iout++] = chars[j];
            }
            count = 0;
        }

        for (int j = count; j < (remainder - count); j++) {
            bytes[j] = in[off + iin++];
        }
        count = remainder;

        if (finalize && remainder > 0) {
            encode();
            for (int j = 0; j < chars.length; j++) {
                out[iout++] = chars[j];
            }
            count = 0;
        }

        return out;
    }

    private void encode() {
        for (int i = count; i < bytes.length; i++) {
            bytes[i] = 0x00; // zero padding
        }
        int pad = ((bytes.length - count) * 8) / bits;
        int index = 0;
        int available = 8;
        for (int i = 0; i < (chars.length - pad); i++) {
            int discarded = 32 - available;
            int base = ((bytes[index] & 0xFF) << discarded) >>> discarded;
            if (available >= bits) {
                base >>>= (available - bits);
                available -= bits;
            } else {
                int needed = bits - available;
                base <<= needed;
                base |= ((bytes[++index] & 0xFF) >>> (available = 8 - needed));
            }
            chars[i] = alphabet[base];
        }
        for (int i = (chars.length - pad); i < chars.length; i++) {
            chars[i] = '=';
        }
    }


    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }


    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    private char[] alphabet;

    private int bits;
    private byte[] bytes;
    private int count;
    private char[] chars;

    private boolean finalized;
}