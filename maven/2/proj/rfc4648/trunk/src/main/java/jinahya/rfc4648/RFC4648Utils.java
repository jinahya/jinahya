package jinahya.rfc4648;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com>Jin Kwon</a>
 */
class RFC4648Utils {


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
        return b == 0 ? a : greatestCommonDivisor(b, a % b);
    }


    /**
     *
     *
     * @param alphabet
     * @return number of bits per character
     */
    public static int bitsPerChar(final String alphabet) {
        return (int)(Math.log(alphabet.length())/Math.log(2.0d));
    }


    /**
     *
     *
     * @param bitsPerChar
     * @return number of bytes per word
     */
    public static int bytesPerWord(final int bitsPerChar) {
        return leastCommonMultiple(8, bitsPerChar) / 8;
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

        return bytesPerWord * 8 / bitsPerChar;
    }


    private RFC4648Utils() {
        super();
    }
}
