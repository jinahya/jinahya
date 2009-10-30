package jinahya.rfc4648;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com>Jin Kwon</a>
 */
final class RFC4648Utils {


    public static final int OCTET_SIZE = 8;


    /**
     *
     *
     * @param a first operand
     * @param b second operand
     * @return the value of least common mulple
     */
    public static int leastCommonMultiple(final int a, final int b) {
        return (a * b) / greatestCommonDivisor(a, b);
    }


    /**
     *
     *
     * @param a first operand
     * @param b second operand
     * @return the value of greatest common divisor
     */
    public static int greatestCommonDivisor(final int a, final int b) {
        if (b == 0) {
            return a;
        } else {
            return greatestCommonDivisor(b, a % b);
        }
    }


    /**
     *
     *
     * @param alphabet
     * @return number of bits per character
     */
    public static int bitsPerChar(final String alphabet) {
        return (int) (Math.log(alphabet.length()) / Math.log(2.0d));
    }


    /**
     *
     *
     * @param bitsPerChar
     * @return number of bytes per word
     */
    public static int bytesPerWord(final int bitsPerChar) {
        return leastCommonMultiple(OCTET_SIZE, bitsPerChar) / OCTET_SIZE;
    }


    /**
     *
     *
     * @param bytesPerWord
     * @param bitsPerChar
     * @return number of characters per word
     */
    public static int charsPerWord(final int bytesPerWord,
                                   final int bitsPerChar) {

        return bytesPerWord * OCTET_SIZE / bitsPerChar;
    }


    private RFC4648Utils() {
        super();
    }
}
